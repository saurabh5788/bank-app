package com.bank.service.endpointimpl;

import javax.jws.WebService;

import com.bank.service.dto.response.AccountTypeDTO;
import com.bank.service.dto.response.AccountTypeWrapperDTO;
import com.bank.service.endpointinterface.AccountServiceInterface;

@WebService(endpointInterface = "com.bank.service.endpointinterface.AccountServiceInterface")
public class AccountServiceImpl implements AccountServiceInterface {

	@Override
	public AccountTypeWrapperDTO getAccountTypeList() {
		AccountTypeWrapperDTO accountTypeWrapperDTO = new AccountTypeWrapperDTO();
		
		AccountTypeDTO accountTypeDTO = new AccountTypeDTO();
		accountTypeDTO.setCode("CC");
		accountTypeDTO.setValue("Credit Card");
		accountTypeDTO.setDescription("Account Credit Card");
		
		accountTypeWrapperDTO.getAccountTypeList().add(accountTypeDTO);
		accountTypeDTO = new AccountTypeDTO();
		accountTypeDTO.setCode("CC");
		accountTypeDTO.setValue("Credit Card");
		accountTypeDTO.setDescription(System.getProperty("propertyName"));
		accountTypeWrapperDTO.getAccountTypeList().add(accountTypeDTO);		
		
		return accountTypeWrapperDTO;
	}

	@Override
	public AccountTypeDTO getAccountType(String accountTypeCode) {
		AccountTypeDTO accountTypeDTO = new AccountTypeDTO();
		accountTypeDTO.setCode(accountTypeCode);
		accountTypeDTO.setValue("Credit Card");
		accountTypeDTO.setDescription("Account Credit Card");
		return accountTypeDTO;
	}
}
