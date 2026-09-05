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
                <title>SBI Pune Branches</title>
                <style>
                    body { font-family: 'Segoe UI', Tahoma, sans-serif; padding: 30px; background-color: #f8fafc; }
                    h1 { color: #1e3a8a; border-bottom: 2px solid #3b82f6; padding-bottom: 10px; }
                    table { width: 100%; border-collapse: collapse; margin-top: 20px; background: white; box-shadow: 0 1px 3px rgba(0,0,0,0.1); }
                    th, td { border: 1px solid #cbd5e1; padding: 12px; text-align: left; }
                    th { background-color: #1e40af; color: white; }
                    tr:nth-child(even) { background-color: #f1f5f9; }
                    .nav { margin-bottom: 20px; }
                    .nav a { margin-right: 15px; color: #2563eb; font-weight: bold; text-decoration: none; }
                </style>
            </head>
            <body>
                <div class="nav">
                    <a href="/">🏠 Home</a>
                    <a href="/branches">🏢 Pune Branches</a>
                    <a href="/services">💳 Bank Services</a>
                    <a href="/health">🟢 Health Status (/health)</a>
                </div>

                <h1>🏛️ State Bank of India - Pune Branches</h1>
                <p>List of 10 major operational branches in Pune city:</p>

                <table>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Branch Name</th>
                            <th>IFSC Code</th>
                            <th>Location / Area</th>
                            <th>Contact No</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr><td>1</td><td>Pune Main Branch</td><td>SBIN0000454</td><td>Collectorate Compound, Shivajinagar</td><td>020-26123451</td></tr>
                        <tr><td>2</td><td>Kothrud Branch</td><td>SBIN0001234</td><td>Karve Road, Kothrud</td><td>020-25432100</td></tr>
                        <tr><td>3</td><td>Deccan Gymkhana Branch</td><td>SBIN0004321</td><td>FC Road, Deccan</td><td>020-25678901</td></tr>
                        <tr><td>4</td><td>Hinjewadi IT Park Branch</td><td>SBIN0008899</td><td>Phase 1, Hinjewadi</td><td>020-66778899</td></tr>
                        <tr><td>5</td><td>Viman Nagar Branch</td><td>SBIN0011223</td><td>Symbiosis Road, Viman Nagar</td><td>020-26633445</td></tr>
                        <tr><td>6</td><td>Aundh Branch</td><td>SBIN0014455</td><td>DP Road, Aundh</td><td>020-25889900</td></tr>
                        <tr><td>7</td><td>Magarpatta City Branch</td><td>SBIN0016789</td><td>Hadapsar, Magarpatta</td><td>020-26890011</td></tr>
                        <tr><td>8</td><td>Baner Branch</td><td>SBIN0018877</td><td>Baner Road, Baner</td><td>020-27291122</td></tr>
                        <tr><td>9</td><td>Pimpri Branch</td><td>SBIN0000789</td><td>Old Mumbai-Pune Highway, Pimpri</td><td>020-27423344</td></tr>
                        <tr><td>10</td><td>Swargate Branch</td><td>SBIN0003344</td><td>Tilak Road, Swargate</td><td>020-24445566</td></tr>
                    </tbody>
                </table>
            </body>
            </html>
            """;
    }

    // 2nd RESTful URL: HTML Page displaying Bank Name & Table of All Services Provided
    @GetMapping(value = "/services", produces = MediaType.TEXT_HTML_VALUE)
    public String getBankServices() {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>SBI Services</title>
                <style>
                    body { font-family: 'Segoe UI', Tahoma, sans-serif; padding: 30px; background-color: #f8fafc; }
                    h1 { color: #15803d; border-bottom: 2px solid #22c55e; padding-bottom: 10px; }
                    table { width: 100%; border-collapse: collapse; margin-top: 20px; background: white; box-shadow: 0 1px 3px rgba(0,0,0,0.1); }
                    th, td { border: 1px solid #cbd5e1; padding: 12px; text-align: left; }
                    th { background-color: #166534; color: white; }
                    tr:nth-child(even) { background-color: #f0fdf4; }
                    .nav { margin-bottom: 20px; }
                    .nav a { margin-right: 15px; color: #16a34a; font-weight: bold; text-decoration: none; }
                </style>
            </head>
            <body>
                <div class="nav">
                    <a href="/">🏠 Home</a>
                    <a href="/branches">🏢 Pune Branches</a>
                    <a href="/services">💳 Bank Services</a>
                    <a href="/health">🟢 Health Status (/health)</a>
                </div>

                <h1>🏛️ State Bank of India - Services Provided</h1>
                <p>Comprehensive list of banking services offered to customers:</p>

                <table>
                    <thead>
                        <tr>
                            <th>Service ID</th>
                            <th>Service Name</th>
                            <th>Category</th>
                            <th>Description</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr><td>SVC-101</td><td>Savings Account</td><td>Personal Banking</td><td>Interest-bearing deposit account with net banking & debit card.</td></tr>
                        <tr><td>SVC-102</td><td>Home Loan</td><td>Loans & Lending</td><td>Affordable interest rates for purchasing or constructing houses.</td></tr>
                        <tr><td>SVC-103</td><td>Current Account</td><td>Corporate Banking</td><td>Zero-limit transaction account for business and commercial use.</td></tr>
                        <tr><td>SVC-104</td><td>Fixed Deposit (FD)</td><td>Investments</td><td>Guaranteed high returns with flexible tenure options.</td></tr>
                        <tr><td>SVC-105</td><td>Internet & Mobile Banking</td><td>Digital Services</td><td>24x7 online funds transfer via YONO, NEFT, RTGS, and IMPS.</td></tr>
                        <tr><td>SVC-106</td><td>Credit & Debit Cards</td><td>Cards</td><td>Global shopping privileges with reward points and airport lounge access.</td></tr>
                        <tr><td>SVC-107</td><td>Personal & Car Loans</td><td>Loans & Lending</td><td>Quick approval personal and vehicle financing options.</td></tr>
                    </tbody>
                </table>
            </body>
            </html>
            """;
    }

    // Health Check Endpoint returning status UP
    @GetMapping("/health")
    public Map<String, Object> getHealthStatus() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        health.put("application", "BankServicesApp");
        health.put("database", "UP");
        return health;
    }
}
