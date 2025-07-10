package com.loja.suplementos.sale;

import com.loja.suplementos.sale.domain.Sale;
import com.loja.suplementos.sale.dto.CityStatsDTO;
import com.loja.suplementos.sale.dto.ClientPurchaseReportDTO;
import com.loja.suplementos.sale.dto.ProductSalesReportDTO;
import com.loja.suplementos.sale.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SaleReportService {

    private final SaleRepository saleRepository;

    public SaleReportService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Map<String, Object>> getCitiesWithMostSales() {
        List<Sale> allSales = saleRepository.findAll();

        Map<String, CityStatsDTO> cityStatsMap = new HashMap<>();

        for (Sale sale : allSales) {
            if (sale.getShipping().getDeliveryAddress() != null) {
                String city = sale.getShipping().getDeliveryAddress().getCity();
                double value = sale.getPayment().getAmount() != null ? sale.getPayment().getAmount().doubleValue() : 0.0;

                CityStatsDTO stats = cityStatsMap.getOrDefault(city, new CityStatsDTO(city));
                stats.addSale(value);
                cityStatsMap.put(city, stats);
            }
        }

        return cityStatsMap.values().stream()
            .map(stats -> {
                Map<String, Object> map = new HashMap<>();
                map.put("city", stats.getCity());
                map.put("salesCount", stats.getSaleCount());
                map.put("totalSalesValue", String.format("%.2f", stats.getTotalValue()));
                return map;
            })
            .sorted((a, b) -> Integer.compare((Integer) b.get("salesCount"), (Integer) a.get("salesCount")))
            .collect(Collectors.toList());
    }

    public List<ClientPurchaseReportDTO> getClientsWithMostPurchaseValue() {
        List<Sale> sales = saleRepository.findAll();

        Map<String, ClientPurchaseReportDTO> reportMap = new HashMap<>();

        for (Sale sale : sales) {
            String key = sale.getCustomer().getName() + "|" + sale.getCustomer().getLastName();
            reportMap.compute(key, (k, v) -> {
                if (v == null) {
                    v = new ClientPurchaseReportDTO();
                    v.setName(sale.getCustomer().getName());
                    v.setLastName(sale.getCustomer().getLastName());
                }
                v.setPurchaseCount(v.getPurchaseCount() + 1);
                v.setTotalPurchaseValue(v.getTotalPurchaseValue() + sale.getPayment().getAmount().doubleValue());
                return v;
            });
        }

        return reportMap.values().stream()
            .sorted(Comparator.comparingDouble(ClientPurchaseReportDTO::getTotalPurchaseValue).reversed())
            .collect(Collectors.toList());
    }

    public List<ProductSalesReportDTO> getProductsWithMostSales() {
        List<Sale> sales = saleRepository.findAll();
        Map<String, ProductSalesReportDTO> productMap = new HashMap<>();

        for (Sale sale : sales) {
            if (sale.getSaleItems() != null) {
                for (var item : sale.getSaleItems()) {
                    String productName = item.getProduct().getName();
                    int quantity = item.getQuantity();
                    double value = item.getPrice().doubleValue() * quantity;

                    productMap.computeIfAbsent(productName, ProductSalesReportDTO::new)
                        .addSales(quantity, value);
                }
            }
        }

        return productMap.values().stream()
            .sorted(Comparator.comparingInt(ProductSalesReportDTO::getTotalSales).reversed())
            .collect(Collectors.toList());
    }
}
