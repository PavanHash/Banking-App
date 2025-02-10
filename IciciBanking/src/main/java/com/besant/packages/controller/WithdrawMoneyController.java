package com.besant.packages.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.besant.packages.service.impl.TransactionServiceImpl;
@WebServlet("/withdrawMoney")
public class WithdrawMoneyController  extends HttpServlet{
@Override
public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	TransactionServiceImpl service = new TransactionServiceImpl();
	service.withdrawMoney(req, resp);
}
}
