Operações CRUD:
- Customer [x]
- DeliveryAddress [x]
- Product [x]
- - Brand [x]
- - NutritionalTable [x]
- Sale []
- Payment []
- SaleItem []
- Shipping []

-- Relatórios:
- Vendas organizada por cidade (Venda x Cliente x Endereco)
- Quantidade de vendas com produto (Venda x VendaItem x Produto)
- Clientes com maior valor de compras (Cliente x Vendas x VendaItem(valor))