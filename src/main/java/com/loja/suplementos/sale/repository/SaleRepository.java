package com.loja.suplementos.sale.repository;

import com.loja.suplementos.sale.domain.Sale;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Profile("mongo")
public interface SaleRepository extends MongoRepository<Sale, String> {

    @Aggregation(pipeline = {
                "{ $group: { _id: '$city', totalSales: { $sum: 1 }, totalValue: { $sum: '$value' } } }",
                "{ $sort: { totalSales: -1 } }",
                "{ $project: { _id: 0, city: '$_id', totalSales: 1, totalValue: 1 } }"
        })
    List<Map<String, Object>> getCitiesWithMostSales();

    @Aggregation(pipeline = {
                "{ $group: { _id: { name: '$customer.name', lastName: '$customer.lastName' }, purchaseCount: { $sum: 1 }, totalPurchaseValue: { $sum: '$value' } } }",
                "{ $sort: { totalPurchaseValue: -1 } }",
                "{ $project: { _id: 0, name: '$_id.name', lastName: '$_id.lastName', purchaseCount: 1, totalPurchaseValue: 1 } }"
        })
    List<Map<String, Object>> getClientsWithMostPurchaseValue();

    @Aggregation(pipeline = {
                "{ $unwind: '$saleItems' }",
                "{ $group: { _id: '$saleItems.product.name', totalSales: { $sum: '$saleItems.quantity' }, totalValue: { $sum: { $multiply: ['$saleItems.price', '$saleItems.quantity'] } } } }",
                "{ $sort: { totalSales: -1 } }",
                "{ $project: { _id: 0, productName: '$_id', totalSales: 1, totalValue: 1 } }"
        })
    List<Map<String, Object>> getProductsWithMostSales();
}
