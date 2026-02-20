package Project;

import java.util.Scanner;

public class BankLoanApplicationSystem {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int user;
        int[] accountnumbers = {10001,10002,10003};
        int[] pıncodes = {001,002,003};
        double[] monthlyincomes = {0.0,0.0,0.0};
        double[] loanapplicationamounts = {1.0,1.0,1.0};
        double[] interestrates = {0.0,0.0,0.0};
        double[] monthlypayments = {0.0,0.0,0.0};
        String lastaction="Empty",lastaction1="Empty",lastaction2="Empty",lastaction3="Empty";
        int account = login(accountnumbers,pıncodes);
        while (true){
            System.out.println("""
                           Menu :
                           1-View Account Summary
                           2-Enter/Update Monthly Income
                           3-Apply For a Loan
                           4-Calculate Monthly Payment
                           5-Cancel Loan Application
                           6-Transfer Loan Amount (Simulation)
                           7-Show Last 3 Operations
                           8-Find Best Applicant (Highest Income-to-Loan Ratio)
                           9-Exit
                           """);
            user=input.nextInt();
            switch(user){
                case 1 -> {
                    accountsummary(account,accountnumbers,monthlyincomes,loanapplicationamounts,interestrates,monthlypayments);
                    lastaction="Account statement reviewed.";
                }
                case 2 -> {
                    updatemonthlyincome(account,monthlyincomes);
                    lastaction="Monthly income updated.";
                }
                case 3 -> {
                    applyloanamount(account,loanapplicationamounts);
                    calculateinterestrate(account,interestrates,monthlyincomes);
                    lastaction="Loan application submitted and Interest rate calculated.";
                }
                case 4 -> {
                    calculatemonthlypayments(account,monthlypayments,loanapplicationamounts,interestrates);
                    lastaction="Monthly payment calculated.";
                }
                case 5 -> {
                    cancelloanamount(account,loanapplicationamounts);
                    cancelinterestrate(account,interestrates);
                    cancelmonthlypayment(account,monthlypayments);
                    lastaction="Loan application cancelled, and Interest rate and Monthly payment set to 0.";
                }
                case 6 -> {
                    transferloanamount(account,accountnumbers,loanapplicationamounts);
                    calculatemonthlypayments(account,monthlypayments,loanapplicationamounts,interestrates);
                    lastaction="Loan amount transferred.";
                }
                case 7 -> {
                    printlastthreeactions(lastaction1,lastaction2,lastaction3);
                    lastaction="Last three actions printed.";
                }
                case 8 -> {
                    printbestapplicant(accountnumbers,monthlyincomes,loanapplicationamounts);
                    lastaction="Best applicant calculated.";
                }
                case 9 -> {
                    System.out.println("Have a good day, the program is closing...");
                    System.exit(0);
                }
                default -> {
                    System.out.println("""
                                       You entered an invalid number.
                                       Please try again...
                                       """);
                }
            }
            lastaction3=lastaction2;
            lastaction2=lastaction1;
            lastaction1=lastaction;
        }
        
    }
    
    public static int login(int[] accountnumbers,int[] pıncodes){
        Scanner input = new Scanner(System.in);
        int accountnumber = 0,count=0;
        System.out.println("""
                           Welcome back,
                           Please enter account number for login :
                           """);
        int user = input.nextInt();
        switch(user){
            case 10001 -> accountnumber=accountnumbers[0];
            case 10002 -> accountnumber=accountnumbers[1];
            case 10003 -> accountnumber=accountnumbers[2];
            default -> {
                System.out.println("""
                                    You entered an invalid number...
                                    Have a good day, the program is closing...
                                    """);
                System.exit(0);
            }
        }     
        while(count<5){
            System.out.println("Account Number : "+accountnumber+"\n"
                          +"Please enter PIN for login...");
            user = input.nextInt();
            count++;
            switch(user){
                case 001 -> {
                if(accountnumber==10001)
                    return 1;
                else
                    System.out.println("You enter wrong PIN please try again...\n"
                                  +"(Only "+(5-count)+" right to try)");
            }
                case 002 -> {
                if(accountnumber==10002)
                    return 2;
                else
                    System.out.println("You enter wrong PIN please try again...\n"
                                  +"(Only "+(5-count)+" right to try)");
            }
                case 003 -> {
                if(accountnumber==10003)
                    return 3;
                else
                    System.out.println("You enter wrong PIN please try again...\n"
                                  +"(Only "+(5-count)+" right to try)");
            }
                default -> {
                if(count==5){
                    System.out.println("""
                                       So many invalid try,
                                       System will closing...
                                       """);
                    System.exit(0);
                }
                System.out.println("You enter wrong PIN please try again...\n"
                                  +"(Only "+(5-count)+" right to try)");
                }
            }   
        }
        return 0;
    }
    
    public static void accountsummary(int account,int[] accountnumbers,double[] monthlyincomes,
    double[] loanapplicationamounts,double[] interestrates,double[] monthlypayments){
        Scanner input = new Scanner(System.in);
        System.out.printf(
            "***** Account Summary *****%n"+
            "Account Number : %d%n"+
            "Monthly Income : %.2f%n"+
            "Loan Application Amount : %.2f%n"+
            "Interest Rate : %.3f%n"+
            "Monthly payment : %.3f%n",
            accountnumbers[account-1],
            monthlyincomes[account-1],
            loanapplicationamounts[account-1],
            interestrates[account-1],
            monthlypayments[account-1]);
    }
    
    public static double[] updatemonthlyincome(int account,double[] monthlyicomes){
        Scanner input = new Scanner(System.in);
        int count=0;
        while(count==0){
            System.out.println("Enter the new monthly income:");
            int user = input.nextInt();
            if(user>=0){
                monthlyicomes[account-1]=user;
                count++;
                System.out.println("""
                                   Monthly income has been succsesfully updated.
                                   Returning main menu...
                                   """);
            }
            else{
                System.out.println("""
                                   Monthly income must be greater than 0.
                                   Please try again...
                                   """);
            } 
        }
        return monthlyicomes;
    }
    
    public static double[] applyloanamount(int account,double[] loanapplicationamounts){
        Scanner input = new Scanner(System.in);
        int count=0,user;
        while(count==0){
            System.out.println("Enter the new loan amount :");
            user = input.nextInt();
            if(user>=0){
                loanapplicationamounts[account-1]=user;
                System.out.println("""
                                   Loan amount has been succsesfully updated.
                                   Calculating new interest rate...
                                   """);
                count++;
            }
            else{
                System.out.println("""
                                   Loan amount must be greater than 0.
                                   Please try again...
                                   """);
            }
        }
        return loanapplicationamounts;
    }
    
    public static double[] calculateinterestrate(int account,double[] interestrates,double[] monthlyincomes){
        if(monthlyincomes[account-1]>=80_000){
            interestrates[account-1]=0.012;
        }
        else if(monthlyincomes[account-1]>=40_000){
            interestrates[account-1]=0.018;
        }
        else{
            interestrates[account-1]=0.024;
        } 
        return interestrates;
    }
   
    public static double[] calculatemonthlypayments(int account,double[] monthlypayments,double[] loanapplicationamounts,double[] interestrates){
        monthlypayments[account-1]=loanapplicationamounts[account-1]*(1+interestrates[account-1]);
        System.out.println("New monthly payment amount calculated as : "+monthlypayments[account-1]);
        return monthlypayments;
    }
    
    public static double[] cancelloanamount(int account,double[]loanapplicationamounts){
        loanapplicationamounts[account-1]=0;
        System.out.println("Loan amount was set to 0.");
        return loanapplicationamounts;
    }
    public static double[] cancelinterestrate(int account,double[] interestrates){
        interestrates[account-1]=0;
        System.out.println("Interest rate was set to 0.");
        return interestrates;
    }
    public static double[] cancelmonthlypayment(int account,double[] monthlypayments){
        monthlypayments[account-1]=0;
        System.out.println("Monthly payment was set to 0.");
        return monthlypayments;
    }
    
    public static double[] transferloanamount(int account,int[] accountnumbers,double[] loanapplicationamounts){
        Scanner input = new Scanner(System.in);
        int user,targetaccountnumber=0,controlforloanamount=0,controlfortargetaccountnumber=0;
        while(controlfortargetaccountnumber==0){
            System.out.println("Enter the account number you wish to send money : ");
            user = input.nextInt();
            for(int i=0;i<accountnumbers.length;i++){
                if(accountnumbers[i]==user){
                    targetaccountnumber=i;
                    controlfortargetaccountnumber++;
                }
            }
            if(controlfortargetaccountnumber==0 || accountnumbers[account-1]==accountnumbers[targetaccountnumber]){
                System.out.println("""
                                   You entered an invalid number.
                                   Returning main menu...
                                   """);
                controlforloanamount=1;
                controlfortargetaccountnumber=1;
            }
        }
        while(controlforloanamount==0){
            System.out.println("Please enter the amount to be sent:");
            user=input.nextInt();
            if(user>=0){  
                loanapplicationamounts[account-1]=loanapplicationamounts[account-1]-user;
                loanapplicationamounts[targetaccountnumber]=loanapplicationamounts[targetaccountnumber]+user;
                controlforloanamount++;
            }
            else{
                System.out.println("""
                                   Loan amount must be greater than 0.
                                   Please try again...
                                   """);
            } 
        }        
        return loanapplicationamounts;
    }

    public static void printlastthreeactions(String lastaction1,String lastaction2,String lastaction3){
        System.out.println("***Last Three Actions***\n"
                          +"1-)"+lastaction1+"\n"
                          +"2-)"+lastaction2+"\n"
                          +"3-)"+lastaction3+"\n");
    }
    
    public static void printbestapplicant(int[]accountnumbers,double[] monthlyincomes,double[] loanapplicationamounts){
        double[] scores = new double[3];
        int best = 0;
        scores[0]=monthlyincomes[0]/loanapplicationamounts[0];
        scores[1]=monthlyincomes[1]/loanapplicationamounts[1];
        scores[2]=monthlyincomes[2]/loanapplicationamounts[2];
        
        if(scores[0]>scores[1] && scores[0]>scores[2])
            best=0;
        if(scores[1]>scores[0] && scores[1]>scores[2])
            best=1;
        if(scores[2]>scores[1] && scores[2]>scores[0])
            best=2;
        
        System.out.printf("***Best Applicant***%n"+
                           "Account Number: %d%n"+
                           "Monthly Income: %.2f%n"+
                           "Computed Score: %.2f%n"
                           ,accountnumbers[best]
                           ,monthlyincomes[best]
                           ,loanapplicationamounts[best]);
    }
}


