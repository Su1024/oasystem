package com.oasystem.constant;

/**
 * @ClassName OperLogConstant
 * @Description TODO
 * @Author zhangz
 * @Date 2020/2/8 4:18 下午
 */
public enum OperLogEnum {
    // 新增
    ADD("1", "新增"),
    UPDATE("2", "修改"),
    DELETE("3", "删除");

    private String code;
    private String value;

    OperLogEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String msg(String code) {
        for (OperLogEnum m : OperLogEnum.values()) {
            if (m.getCode() == code) {
                return m.getValue();
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        System.out.println(OperLogEnum.ADD.getCode());
    }
}
