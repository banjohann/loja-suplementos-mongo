package com.loja.suplementos.home;

import com.loja.suplementos.sale.SaleReportService;
import com.loja.suplementos.sale.repository.SaleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/reports")
public class ReportController {

    private SaleRepository saleRepository;
    private SaleReportService saleReportService;

    @GetMapping("/cities-with-most-sales")
    public String getCitiesWithMostSales(Model model) {
        var reports = saleReportService.getCitiesWithMostSales();

        model.addAttribute("reports", reports);
        return "reports/cities-with-most-sales";
    }

    @GetMapping("/clients-with-most-purchase-value")
    public String getClientsWithMostPurchaseValue(Model model) {
        var reports = saleReportService.getClientsWithMostPurchaseValue();

        model.addAttribute("reports", reports);
        return "reports/clients-with-most-purchase-value";
    }

    @GetMapping("/products-with-most-sales")
    public String getProductsWithMostSales(Model model) {
        var reports = saleReportService.getProductsWithMostSales();

        model.addAttribute("reports", reports);
        return "reports/products-with-most-sales";
    }
}