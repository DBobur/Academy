package com.example.academy.core.domain.mapper.attandance;

import com.example.academy.core.domain.request.topic.module.attandance.AttendanceDetailRequest;
import com.example.academy.core.domain.response.attandance.AttendanceWithDetailResponse;
import com.example.academy.modules.attendance.entity.AttendanceDetailEntity;
import com.example.academy.modules.attendance.entity.AttendanceEntity;
import com.example.academy.modules.user.entity.UserEntity;

public class AttendanceWithDetailMapper {


        public static AttendanceDetailEntity requestToEntity(AttendanceDetailRequest request, UserEntity student) {
            return AttendanceDetailEntity.builder()
                    .date(request.getDate())
                    .isPresent(request.isPresent())
                    .student(student)
                    .build();
        }



    public static AttendanceWithDetailResponse entityToResponse(AttendanceDetailEntity entity) {
        return AttendanceWithDetailResponse.builder()
                .id(entity.getId())
                .studentId(entity.getStudent().getId())
                .studentFullName(entity.getStudent().getFullName())
                .date(entity.getDate())
                .isPresent(entity.isPresent())
                .build();
    }

}
