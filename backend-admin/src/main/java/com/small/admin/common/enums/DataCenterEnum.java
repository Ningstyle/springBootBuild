package com.small.admin.common.enums;

public enum DataCenterEnum {
    USER("ADMIN", 0);

    private String type;
    private int value;

    private DataCenterEnum(String type, int value) {
        this.type = type;
        this.value = value;
    }

    public static int getValueByType(String type) throws Exception {
        DataCenterEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            DataCenterEnum dc = var1[var3];
            if (dc.getType().equals(type)) {
                return dc.getValue();
            }
        }

        throw new Exception("未找到相应类型");
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}