class UserRegistration {
    private static final double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0;
    private static final double VIP_DISCOUNT_UNDER_18 = 20.0;
    private static final double VIP_BASE_FEE = 100.0;

    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private double feeToCharge;
    private int cvv;

    private String userType;
    private boolean emailValid;
    private boolean minorAndBirthday;
    private boolean minor;
    private boolean ageValid;
    private boolean cardNumberValid;
    private boolean cardStillValid;
    private boolean validCVV;

    Scanner sc = new Scanner(System.in);

    public void registration() {
        minorAndBirthday = false;
        minor = false;

        System.out.println("\n===== ERyder Registration =====");
        System.out.println("Welcome to the ERyder Registration.");
        System.out.println("1. Register as a Regular User");
        System.out.println("2. Register as a VIP User");
        System.out.print("Please enter your choice (1 or 2): ");
        String choice = sc.next();
        sc.nextLine();

        if (choice.equals("1")) {
            userType = "Regular User";
        } else {
            userType = "VIP User";
        }

        System.out.print("Please enter your full name: ");
        fullName = sc.nextLine();

        System.out.print("Please enter your email address: ");
        emailAddress = sc.nextLine();
        emailValid = analyseEmail(emailAddress);

        System.out.print("Please enter your date of birth (format YYYY-MM-DD): ");
        dateOfBirth = sc.nextLine();
        LocalDate dob = LocalDate.parse(dateOfBirth);
        ageValid = analyseAge(dob);

        System.out.println("We only accept VISA, MasterCard, and American Express cards.");
        System.out.print("Please enter your card number: ");
        cardNumber = sc.nextLong();
        cardNumberValid = analyseCardNumber(cardNumber);

        System.out.print("Please enter your card expiry date (format MM/YY): ");
        cardExpiryDate = sc.next();
        cardStillValid = analyseCardExpiryDate(cardExpiryDate);

        System.out.print("Please enter your CVV: ");
        cvv = sc.nextInt();
        validCVV = analyseCVV(cvv);

        finalCheckpoint();
        sc.close();
    }

    private boolean analyseEmail(String email) {
        if (email.contains("@") && email.contains(".")) {
            System.out.println("Email is valid");
            return true;
        } else {
            System.out.println("Invalid email address. Going back to the start of the registration");
            registration();
            return false;
        }
    }

    // 年龄验证
    private boolean analyseAge(LocalDate dob) {
        LocalDate now = LocalDate.now();
        int years = Period.between(dob, now).getYears();

        boolean isBirthday = (dob.getMonth() == now.getMonth() && dob.getDayOfMonth() == now.getDayOfMonth());

        if (userType.equals("VIP User")) {
            if (years > 12 && years <= 18) {
                if (isBirthday) {
                    System.out.println("Happy Birthday!You get 25% discount on the VIP subscription fee for being born today and being under 18!");
                    minorAndBirthday = true;
                } else {
                    System.out.println("You get 20% discount on the VIP subscription fee for being under 18!");
                    minor = true;
                }
            }
        }

        if (years <= 12 || years > 120) {
            System.out.println("It looks like you're either too young or already deceased. Sorry, you can't be our user.");
            System.exit(0);
        }

        return true;
    }

    private boolean analyseCardNumber(long cardNum) {
        String cardNumStr = String.valueOf(cardNum);
        int len = cardNumStr.length();

        if (len < 2) {
            System.out.println("Invalid card number. Going back to the start of the registration.");
            registration();
            return false;
        }

        int firstTwoDigits = Integer.parseInt(cardNumStr.substring(0, 2));
        int firstFourDigits = 0;
        if (len >= 4) {
            firstFourDigits = Integer.parseInt(cardNumStr.substring(0, 4));
        }

        if ((len == 13 || len == 16) && cardNumStr.startsWith("4")) {
            cardProvider = "VISA";
            return true;
        }

        else if (len == 16 &&
                ((firstTwoDigits >= 51 && firstTwoDigits <= 55) ||
                        (firstFourDigits >= 2221 && firstFourDigits <= 2720))) {
            cardProvider = "MasterCard";
            return true;
        }

        else if (len == 15 && (cardNumStr.startsWith("34") || cardNumStr.startsWith("37"))) {
            cardProvider = "American Express";
            return true;
        }

        else {
            System.out.println("Sorry, but we accept only VISA, MasterCard, or American Express cards. Please try again with a valid card.Going back to the start of the registration. ");
            registration();
            return false;
        }
    }

    private boolean analyseCardExpiryDate(String expiry) {
        int month = Integer.parseInt(expiry.substring(0, 2));
        int year = 2000 + Integer.parseInt(expiry.substring(3, 5));

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();

        if (year > currentYear || (year == currentYear && month >= currentMonth)) {
            System.out.println("The card is still valid.");
            return true;
        } else {
            System.out.println("Sorry, your card has expired. Please use another card. Returning to the start of the registration process...");
            registration();
            return false;
        }
    }

    private boolean analyseCVV(int cvv) {
        String cvvStr = String.valueOf(cvv);

        boolean valid = false;
        if (cardProvider.equals("American Express") && cvvStr.length() == 4) {
            valid = true;
        } else if ((cardProvider.equals("VISA") || cardProvider.equals("MasterCard")) && cvvStr.length() == 3) {
            valid = true;
        }

        if (valid) {
            System.out.println("The card CVV is valid.");
            return true;
        } else {
            System.out.println("The provided card CVV is invalid. Returning to the start of the registration process.");
            registration();
            return false;
        }
    }

    private void finalCheckpoint() {
        if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV) {
            chargeFees();
        } else {
            System.out.println("\nSorry, your registration was not successful for the following reasons:");
            if (!emailValid) System.out.println("Invalid email address.");
            if (!ageValid) System.out.println("Invalid age.");
            if (!cardNumberValid) System.out.println("Invalid card number.");
            if (!cardStillValid) System.out.println("Card has expired.");
            if (!validCVV) System.out.println("Invalid CVV.");
            System.out.println("Returning to the start of the registration process.");
            registration();
        }
    }

    private void chargeFees() {
        if (minorAndBirthday) {
            feeToCharge = VIP_BASE_FEE * (100 - VIP_DISCOUNT_UNDER_18_BIRTHDAY) / 100;
        } else if (minor) {
            feeToCharge = VIP_BASE_FEE * (100 - VIP_DISCOUNT_UNDER_18) / 100;
        } else {
            feeToCharge = VIP_BASE_FEE;
        }

        String cardStr = String.valueOf(cardNumber);
        String lastFour = cardStr.substring(cardStr.length() - 4);
        System.out.printf("\nThank you for your payment. %.2f has been charged to your card with the last four digits ****%s.\n", feeToCharge, lastFour);
    }

    @Override
    public String toString() {
        String cardNumberStr = String.valueOf(cardNumber);
        int len = cardNumberStr.length();

        String censoredPart = cardNumberStr.substring(0, len - 4).replaceAll(".", "*");
        String lastFourDigits = cardNumberStr.substring(len - 4);
        String censoredNumber = censoredPart + lastFourDigits;

        return "\n===== Registration Successful! Here are your details =====" +
                "\nUser Type：" + userType +
                "\nFull Name：" + fullName +
                "\nEmail Address：" + emailAddress +
                "\nDate of Birth：" + dateOfBirth +
                "\nCard Number：" + censoredNumber +
                "\nCard Provider：" + cardProvider +
                "\nCard Expiry Date：" + cardExpiryDate +
                "\n========================================";
    }
}
