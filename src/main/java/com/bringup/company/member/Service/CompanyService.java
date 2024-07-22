package com.bringup.company.member.Service;

import com.bringup.company.member.DTO.request.JoinDto;
import com.bringup.company.member.Entity.Company;
import com.bringup.company.member.exception.CompanyException;

import static com.bringup.common.enums.MemberErrorCode.DUPLICATED_MEMBER_EMAIL;
import static com.bringup.common.enums.MemberErrorCode.DUPLICATED_MEMBER_PHONE_NUMBER;

public class CompanyService {
    public String joinCompany(JoinDto joinDto) {
        // 아이디 중복 체크
        if (companyRepository.existsByUserid(joinDto.getId())) {
            throw new CompanyException(DUPLICATED_MEMBER_EMAIL);
        } else if (companyRepository.existsByEmail(joinDto.getManager_phone())) {
            throw new CompanyException(DUPLICATED_MEMBER_PHONE_NUMBER);
        }

        Company company = ConvertUtil.toDtoOrEntity(joinDto, Company.class);
        company.setManagerEmail(joinDto.getId());
        company.setCompanyPassword(passwordEncoder.encode(joinDto.getPassword()));
        company.setCompanyName(joinDto.getCompany_name());
        company.setCompanyPhoneNumber(joinDto.getCompany_phone());
        company.setCompanyAddress(joinDto.getAddress());
        company.setCompanyContent(joinDto.getContent());
        company.setCompanyWelfare(joinDto.getWelfare());
        company.setCompanyVision(joinDto.getVision());
        company.setCompanyHistory(joinDto.getHistory());
        company.setManagerName(joinDto.getManager_name());
        company.setManagerPhoneNumber(joinDto.getManager_phone());
        company.setCompanySize(joinDto.getCompanysize());
        company.setCompanyLogo(joinDto.getLogo());
        company.setOpenCVKey(joinDto.getCv_key());

        String id = companyRepository.save(company).getCompanyName();

        return id;
    }
}