package ru.ivanov.evgeny.eventscheduler.services.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.FileRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.AccountDto;

import java.util.UUID;

@Component
public class AccountMapper {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Autowired
    private FileRepository fileRepository;

    public Account mapToEntity(AccountDto accountDto) {
        Account account = new Account();

        account.setUsername(accountDto.getUsername());
        account.setPassword(accountDto.getPassword());
        account.setEmail(accountDto.getEmail());
        account.setFirstName(accountDto.getFirstName());
        account.setLastName(accountDto.getLastName());
        account.setAge(accountDto.getAge());
        account.setCity(accountDto.getCity());
        account.setGender(accountDto.getGender());
        if(accountDto.getFileInfo()==null){

            account.setImageInfo(fileRepository.findById(UUID.fromString("e4fe2d4c-cad1-11eb-b8bc-0242ac130003")).get());
        }

        return account;
    }

    public AccountDto mapToDto(Account account) {

        AccountDto accountDto = new AccountDto();

        accountDto.setId(account.getId());
        accountDto.setUsername(account.getUsername());
        accountDto.setEmail(account.getEmail());
        accountDto.setFirstName(account.getFirstName());
        accountDto.setLastName(account.getLastName());
        accountDto.setAge(account.getAge());
        accountDto.setCity(account.getCity());
        accountDto.setGender(account.getGender());
        if (account.getImageInfo() != null) accountDto.setFileInfo(fileInfoMapper.mapToDto(account.getImageInfo()));

        return accountDto;
    }
}
