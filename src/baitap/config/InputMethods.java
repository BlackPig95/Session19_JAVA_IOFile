package baitap.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class InputMethods
{
    private InputMethods()
    {
    }

    private final static Scanner scanner = new Scanner(System.in);
    private static final String ERROR_ALERT = "Định dạng không hợp lệ, hoặc ngoài phạm vi! Vui lòng thử lại.";
    private static final String EMPTY_ALERT = "Trường nhập vào không thể để trống! Vui lòng thử lại.";
    private static final String DATE_FORMAT = "Vui lòng nhập đúng định dạng: dd/MM/yyyy";


    private static String getInput()
    {
        return scanner.nextLine();
    }

    public static String nextLine()
    {
        while (true)
        {
            String result = getInput();
            if (result.isBlank())
            {
                System.err.println(EMPTY_ALERT);
                continue;
            }
            return result;
        }
    }

    public static char nextChar()
    {
        while (true)
        {
            String newChar = nextLine().trim();
            if (newChar.length() > 1)
            {
                System.out.println("Vui lòng nhập chỉ duy nhất 1 ký tự");
                continue;
            }
            return newChar.charAt(0);
        }
    }

    public static boolean nextBoolean()
    {
        while (true)
        {
            String bool = nextLine();
            if (bool.equalsIgnoreCase("true") || bool.equalsIgnoreCase("false"))
                return Boolean.parseBoolean(bool);
            System.out.println("Vui lòng nhập chính xác true hoặc false");
        }
    }

    public static byte nextByte()
    {
        while (true)
        {
            try
            {
                return Byte.parseByte(nextLine());
            } catch (NumberFormatException errException)
            {
                System.out.println(ERROR_ALERT);
            }
        }
    }

    public static short nextShort()
    {
        while (true)
        {
            try
            {
                return Short.parseShort(nextLine());
            } catch (NumberFormatException errException)
            {
                System.out.println(ERROR_ALERT);
            }
        }
    }

    public static int nextInt()
    {
        while (true)
        {
            try
            {
                return Integer.parseInt(nextLine());
            } catch (NumberFormatException errException)
            {
                System.out.println(ERROR_ALERT);
            }
        }
    }

    public static long nextLong()
    {
        while (true)
        {
            try
            {
                return Long.parseLong(nextLine());
            } catch (NumberFormatException errException)
            {
                System.out.println(ERROR_ALERT);
            }
        }
    }

    public static float nextFloat()
    {
        while (true)
        {
            try
            {
                return Float.parseFloat(nextLine());
            } catch (NumberFormatException errException)
            {
                System.out.println(ERROR_ALERT);
            }
        }
    }

    public static double nextDouble()
    {
        while (true)
        {
            try
            {
                return Double.parseDouble(nextLine());
            } catch (NumberFormatException errException)
            {
                System.out.println(ERROR_ALERT);
            }
        }
    }

    public static Date nextDate()
    {
        SimpleDateFormat formatter = new SimpleDateFormat(CONSTANT.DDMMYYYY);
        while (true)
        {
            try
            {
                return formatter.parse(nextLine());
            } catch (ParseException errException)
            {
                System.err.println(DATE_FORMAT);
            }
        }
    }

    public static LocalDate nextLocalDate()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CONSTANT.DDMMYYYY);
        while (true)
        {
            String input = nextLine();
            try
            {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException dateTimeParseException)
            {
                System.out.println(DATE_FORMAT);
            }
        }
    }
}
