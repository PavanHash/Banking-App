package com.besant.packages.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.besant.packages.service.impl.TransactionServiceImpl;

@WebServlet("/depositMoney")
public class DepositMoneyController extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		TransactionServiceImpl service = new TransactionServiceImpl();
		service.addMoney(req, resp);
	}
}
