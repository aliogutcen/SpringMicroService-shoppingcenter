package com.ali.dto.request;

import com.ali.repository.enums.EStatus;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfileActivateStatus {
    Long authid;
    EStatus estatus;
}
