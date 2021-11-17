package com.small.business.utils;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * 
 *
 *
 */
public class Utils {
	 public static final int importDataFailCode =2;
	 private static final DateTimeFormatter[] DATE_TIME_FORMATTERS = new DateTimeFormatter[]{
		 DateTimeFormatter.ISO_LOCAL_DATE_TIME, // yyyy-MM-ddTHH:mm:ss
		 DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
		 DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
		 DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"),
		 DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
	 };

	 private static final DateTimeFormatter[] DATE_FORMATTERS = new DateTimeFormatter[]{
         DateTimeFormatter.ISO_LOCAL_DATE, // yyyy-MM-dd
         DateTimeFormatter.ofPattern("yyyy-M-d"),
         DateTimeFormatter.ofPattern("yyyy/M/d"),
         DateTimeFormatter.ofPattern("yyyy/MM/dd"),
	 };
	 public static String getUUID(){
	        UUID uuid = UUID.randomUUID();
	        return uuid.toString().replace("-","");
	    }

	public static String encode(String filename) {
		try{
			return new String(filename.getBytes("UTF-8"), "iso-8859-1");
		}catch(Exception e){
			return filename;
		}
//    	return URLEncoder.encode(filename, "UTF-8");
    }
	public static void closeInputStream(InputStream inputStream) {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeOutPutStream(OutputStream outputStream) {
		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	/**
	 * @param
	 */
//	public static boolean isPlatAdmin() {
//		return hasAnyRole(PlatRoleConstant.PlatAdmin);
//	}

	public static LocalDateTime parse(String s) {
        for (DateTimeFormatter f : DATE_TIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(s, f);
            } catch (Exception e) {
            }
        }
        for (DateTimeFormatter f : DATE_FORMATTERS) {
            try {
                return LocalDate.parse(s, f).atStartOfDay();
            } catch (Exception e) {
            }
        }
        throw new RuntimeException("Invalid time string: " + s);
    }

    public static LocalDateTime parseDateTime(String s) {
        for (DateTimeFormatter f : DATE_TIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(s, f);
            } catch (Exception e) {
            }
        }
        throw new RuntimeException("Invalid time string: " + s);
    }

    public static LocalDate parseDate(String s) {
        for (DateTimeFormatter f : DATE_FORMATTERS) {
            try {
                return LocalDate.parse(s, f);
            } catch (Exception e) {
            }
        }
        throw new RuntimeException("Invalid date string: " + s);
    }

//	public static boolean isPlatTopAnalyst() {
//		return hasAnyRole(PlatRoleConstant.PlatTopAnalyst);
//	}

}
