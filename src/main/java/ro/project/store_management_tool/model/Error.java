package ro.project.store_management_tool.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Error {
    String errorCode;
    String errorMessage;
}
