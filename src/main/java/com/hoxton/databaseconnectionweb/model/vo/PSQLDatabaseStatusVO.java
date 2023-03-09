package com.hoxton.databaseconnectionweb.model.vo;

import lombok.*;

import java.sql.Timestamp;

/**
 * @author Hoxton on 2023/3/9
 * @since 1.2.0
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PSQLDatabaseStatusVO extends DatabaseStatusVO {
    /**
     * 資料庫名稱
     */
String databaseName;
    /**
     * 後台進程的 ID
     */
Integer pid;
    /**
     * 進程正在使用的用戶的名稱
     */
String username;
    /**
     * 應用程式的名稱
     */
String applicationName;
    /**
     * 進程正在使用的客戶端的 IP 位址
     * ::1 代表localhost
     */
String clientAddress;
    /**
     * 後台進程正在使用的客戶端的主機名稱
     */
String clientHostname;
    /**
     * 正在使用的客戶端的端口號
     */
Integer clientPort;
    /**
     *  後台進程開始執行的時間
     */
Timestamp backendStart;
    /**
     * 後台進程開始執行當前事務的時間（如果有）。
     */
Timestamp xactStart;
    /**
     *  後台進程開始執行當前查詢的時間（如果有）
     */
Timestamp queryStart;
    /**
     * 後台進程最近狀態改變的時間。
     */
Timestamp stateChange;
    /**
     * 如果此後台進程正在等待某個事件，此欄位指示事件的類型；否則，此欄位為空
     */
String waitEventType;
    /**
     * 如果此後台進程正在等待某個事件，此欄位指示事件的名稱；否則，此欄位為空
     */
String waitEvent;
    /**
     * 後台進程的當前狀態, idle, active
     */
String state;
    /**
     * 如果此後台進程正在執行當前事務，此欄位為事務 ID；否則，此欄位為空。
     */
Integer backendXid;
    /**
     * 此後台進程最近一次檢查後台工作者狀態的時間。
     */
Integer backendXmin;
    /**
     *  執行了什麼語句
     */
String query;
    /**
     * 台進程的類型，例如“client backend”或“wal writer”等。
     */
String backendType;

}
