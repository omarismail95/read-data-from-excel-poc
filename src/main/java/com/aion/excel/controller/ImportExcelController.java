package com.aion.excel.controller;

import com.aion.excel.model.EnquiredProduct;
import com.aion.excel.model.EnquiryType;
import com.aion.excel.model.ExcelSheetDataModel;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Import Excel Controller.
 *
 * @author Omar Ismail
 * @project poc
 * @date 18/04/2023
 */
@RestController
public class ImportExcelController {

    @RequestMapping(value = "/import-excel", method = RequestMethod.POST)
    public ResponseEntity<List<ExcelSheetDataModel>> importExcelFile(@RequestParam("file") MultipartFile files) {

        XSSFWorkbook workbook = null;
        List<ExcelSheetDataModel> excelSheetDataModels = new ArrayList<>();

        try {
            workbook = new XSSFWorkbook(files.getInputStream());

            XSSFSheet worksheet = workbook.getSheetAt(0);

            for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
                if (index > 0) {

                    ExcelSheetDataModel excelSheetDataModel = new ExcelSheetDataModel();
                    XSSFRow row = worksheet.getRow(index);

                    populateExcelModel(excelSheetDataModels, excelSheetDataModel, row);
                }
            }
            workbook.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(excelSheetDataModels, HttpStatus.OK);
    }

    private void populateExcelModel(final List<ExcelSheetDataModel> excelSheetDataModels,
                                    final ExcelSheetDataModel excelSheetDataModel,
                                    final XSSFRow row) {

        String member = row.getCell(0).getStringCellValue();
        long enquiryReference = (long) row.getCell(1).getNumericCellValue();
        EnquiredProduct enquiredProduct = EnquiredProduct
                .valueOf(row.getCell(2).getStringCellValue());
        EnquiryType enquiryType = EnquiryType
                .valueOf(row.getCell(3).getStringCellValue());

        long enquiredId = (long) row.getCell(4).getNumericCellValue();

        Date enquiryDate = row.getCell(5).getDateCellValue();

        DateFormat enquiryDateFormat = new SimpleDateFormat("dd-mm-yyyy");
        String enquiryDateString = enquiryDateFormat.format(enquiryDate);

        Date enquiryTime = row.getCell(6).getDateCellValue();

        DateFormat enquiryTimeFormat = new SimpleDateFormat("hh:mm:ss");
        String strDate = enquiryTimeFormat.format(enquiryTime);

        String userId = row.getCell(7).getStringCellValue();

        excelSheetDataModel.setEnquiredId(enquiredId);
        excelSheetDataModel.setEnquiredProduct(enquiredProduct);
        excelSheetDataModel.setEnquiryDate(enquiryDateString);
        excelSheetDataModel.setMember(member);
        excelSheetDataModel.setEnquiryTime(strDate);
        excelSheetDataModel.setEnquiryReference(enquiryReference);
        excelSheetDataModel.setEnquiryType(enquiryType);
        excelSheetDataModel.setUserId(userId);
        excelSheetDataModels.add(excelSheetDataModel);
    }


    @PostMapping("/test")
    ResponseEntity<Response> getVariables(@RequestBody
                                               Object workflowHistoryDto) {

        List<varsMap> varsMapList = new ArrayList<>();

        Response response = new Response();
        response.setResponseStatus("true");



        if (workflowHistoryDto instanceof ArrayList) {
            for (Object test : (ArrayList) workflowHistoryDto) {
                System.out.printf("test" + test);

                if (test instanceof LinkedHashMap) {
                    ((LinkedHashMap) test).forEach((k, v) -> {
                        
                        if (v instanceof LinkedHashMap) {
                            List<Map<String, String>> list = new ArrayList<>();
                            ((LinkedHashMap) v).forEach((k1, v1) -> {
                                Map<String, String> map = new HashMap<>();
                                map.put((String) k1, (String) v1);
                                list.add(map);
                                varsMap varObject = new varsMap();
                                varObject.setVarsMap(list);
                                varsMapList.add(varObject);
//                                testModel.setKey((String) k1);
//                                testModel.setValue((String) v1);
                            });
                        }
                    });
                }
            }
        }
        response.setResult(varsMapList);
        return ResponseEntity.ok(response);
    }
}