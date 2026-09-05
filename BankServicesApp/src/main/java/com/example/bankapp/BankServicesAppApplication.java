package com.example.bankapp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BankServicesAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankServicesAppApplication.class, args);
    }

    // 1st RESTful URL: HTML Page displaying Bank Name & Table of 10 Pune Branches
    @GetMapping(value = "/branches", produces = MediaType.TEXT_HTML_VALUE)
    public String getPuneBranches() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Bank Branches</title>
                <style>
                    body { font-family: sans-serif; padding: 20px; }
                    table { border-collapse: collapse; width: 100%; margin-top: 15px; }
                    th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
                    th { background: #f0f0f0; }
                </style>
            </head>
            <body>
                <h2>State Bank of India - Pune Branches</h2>
                <table>
                    <tr><th>#</th><th>Branch Name</th><th>IFSC Code</th><th>Area</th><th>Contact</th></tr>
                    <tr><td>1</td><td>Pune Main Branch</td><td>SBIN0000454</td><td>Shivajinagar</td><td>020-26123451</td></tr>
                    <tr><td>2</td><td>Kothrud Branch</td><td>SBIN0001234</td><td>Karve Road</td><td>020-25432100</td></tr>
                    <tr><td>3</td><td>Deccan Gymkhana</td><td>SBIN0004321</td><td>FC Road</td><td>020-25678901</td></tr>
                    <tr><td>4</td><td>Hinjewadi Branch</td><td>SBIN0008899</td><td>Phase 1</td><td>020-66778899</td></tr>
                    <tr><td>5</td><td>Viman Nagar Branch</td><td>SBIN0011223</td><td>Symbiosis Road</td><td>020-26633445</td></tr>
                    <tr><td>6</td><td>Aundh Branch</td><td>SBIN0014455</td><td>DP Road</td><td>020-25889900</td></tr>
                    <tr><td>7</td><td>Magarpatta Branch</td><td>SBIN0016789</td><td>Hadapsar</td><td>020-26890011</td></tr>
                    <tr><td>8</td><td>Baner Branch</td><td>SBIN0018877</td><td>Baner Road</td><td>020-27291122</td></tr>
                    <tr><td>9</td><td>Pimpri Branch</td><td>SBIN0000789</td><td>Old Highway</td><td>020-27423344</td></tr>
                    <tr><td>10</td><td>Swargate Branch</td><td>SBIN0003344</td><td>Tilak Road</td><td>020-24445566</td></tr>
                </table>
                <br><a href="/">Back to Home</a>
            </body>
            </html>
            """;
    }

    // 2nd RESTful URL: HTML Page displaying Bank Name & Table of Bank Services Provided
    @GetMapping(value = "/services", produces = MediaType.TEXT_HTML_VALUE)
    public String getBankServices() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Bank Services</title>
                <style>
                    body { font-family: sans-serif; padding: 20px; }
                    table { border-collapse: collapse; width: 100%; margin-top: 15px; }
                    th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
                    th { background: #f0f0f0; }
                </style>
            </head>
            <body>
                <h2>State Bank of India - Services Provided</h2>
                <table>
                    <tr><th>Service ID</th><th>Service Name</th><th>Category</th><th>Description</th></tr>
                    <tr><td>SVC-101</td><td>Savings Account</td><td>Personal</td><td>Interest deposit account with net banking.</td></tr>
                    <tr><td>SVC-102</td><td>Home Loan</td><td>Loans</td><td>House purchase loan options.</td></tr>
                    <tr><td>SVC-103</td><td>Current Account</td><td>Business</td><td>Commercial business transaction account.</td></tr>
                    <tr><td>SVC-104</td><td>Fixed Deposit (FD)</td><td>Investment</td><td>Guaranteed returns deposit scheme.</td></tr>
                    <tr><td>SVC-105</td><td>Net Banking</td><td>Digital</td><td>24x7 online fund transfer.</td></tr>
                    <tr><td>SVC-106</td><td>Debit & Credit Cards</td><td>Cards</td><td>Shopping privileges and reward points.</td></tr>
                    <tr><td>SVC-107</td><td>Personal Loans</td><td>Loans</td><td>Quick approval personal financing.</td></tr>
                </table>
                <br><a href="/">Back to Home</a>
            </body>
            </html>
            """;
    }

    // Health Parameter Endpoint returning status UP
    @GetMapping("/health")
    public Map<String, String> getHealthStatus() {
        Map<String, String> health = new HashMap<>();
        health.put("status", "UP");
        return health;
    }
}
