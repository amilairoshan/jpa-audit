package com.navulia.audit.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class SupplierOutputDTO extends SupplierInputDTO{
    private final long id;
    private final String createdBy;
    private final Date createdDate;
    private final String lastModifiedBy;
    private final Date lastModifiedDate;

    public SupplierOutputDTO(final long id,final String name, final String address,final String contactDetails,final String specialties,final String createdBy,final Date createdDate,final String lastModifiedBy,final Date lastModifiedDate) {
        super(name, address, contactDetails,specialties);
        this.id=id;
        this.createdBy=createdBy;
        this.createdDate=createdDate;
        this.lastModifiedBy=lastModifiedBy;
        this.lastModifiedDate=lastModifiedDate;
    }

}
