Operações CRUD:
- Customer [x]
- DeliveryAddress []
- Payment []
- Product []
- - Brand []
- - NutritionalTable []
- Sale []
- SaleItem []
- Shipping []

-- Relatórios:
- Clientes com vendas entregues (Venda x Cliente x Entrega)
- Quantidade de vendas com produto (Venda x VendaItem x Produto)
- Clientes com maior valor de compras (Cliente x Vendas x VendaItem(valor))