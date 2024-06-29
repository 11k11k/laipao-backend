package com.laioj.project.once;

import com.alibaba.excel.EasyExcel;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ImportExcelTrain {
    public static void main(String[] args) {
        String fileName = "D:\\springcloudTest\\laipao-backend-focus\\src\\main\\resources\\excel\\demo.xlsx";
        List<XingQiuUserInfo> userInfoList = EasyExcel.read(fileName).head(XingQiuUserInfo.class).sheet().doReadSync();
        System.out.println("总数 = " + userInfoList.size());
        Map<String, List<XingQiuUserInfo>> listMap = userInfoList.stream()
                .filter(userInfo -> StringUtils.isNotEmpty(userInfo.getUsername()))
                .collect(Collectors.groupingBy(XingQiuUserInfo::getUsername));
        for (Map.Entry<String, List<XingQiuUserInfo>> stringListEntry : listMap.entrySet()) {

            if (!stringListEntry.getValue().isEmpty()) {
                System.out.println("username= " + stringListEntry.getKey());
                System.out.println("1");
            }
        }

    }
}
