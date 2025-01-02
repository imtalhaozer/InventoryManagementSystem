package com.example.inventorymanagementsystem.service;

import com.example.inventorymanagementsystem.dao.BillItemDao;
import com.example.inventorymanagementsystem.dao.ProductDao;
import com.example.inventorymanagementsystem.dto.cartItem.CartItemResponseDto;
import com.example.inventorymanagementsystem.dto.response.BillItemResponseDto;
import com.example.inventorymanagementsystem.dto.response.Product.ProductResponseDto;
import com.example.inventorymanagementsystem.models.Product;
import com.example.inventorymanagementsystem.util.DbCon;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class BillItemService {
    private BillItemDao billItemDao;
    private ProductDao productDao;

    public BillItemService() throws SQLException, ClassNotFoundException {
        Connection con = DbCon.getConnection();
        this.billItemDao = new BillItemDao(con);
        this.productDao = new ProductDao(con);
    }

    public void addBillItem(String billId, List<CartItemResponseDto> cartItems) {
        billItemDao.addBillItem(billId,cartItems);
    }

    public void confirmBillItem(Long id) {
        BillItemResponseDto billItem = billItemDao.getBillItemById(id);
        productDao.decreaseStockQuantity(billItem.getProductId(), billItem.getQuantity());
        billItemDao.confirmBillItem(id);
    }

    public List<BillItemResponseDto> getBillItemsSupplier(UUID supplierId) {
        List<Long> productIds = productDao.getProductIdListBySupplierId(supplierId);
        return billItemDao.getBillItemsByProductId(productIds);
    }
}
