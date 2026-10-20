package api_teste.ds.models.enums;

import java.util.Objects;

public enum ProfileEnums {

    ADMIN(1, "ROLE_ADMIN"),

    USER(2, "ROLE_USER");

    private Integer code;

    private String description;

    ProfileEnums(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static ProfileEnums toEnum(Integer code) {

        if (Objects.isNull(code)) {
            return null;
        }

        for (ProfileEnums x : ProfileEnums.values()) {

            if (code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + code);
    }
}