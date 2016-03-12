package ru.kev163rus.thefutureguy;

import java.util.Random;

/**
 * Created by Админ on 31.01.2016.
 */
public class Questions {

    static int indexOfQuestion, countOfQuestions;
    static boolean itFastTest, itFullTest, itAddTest, isDebuging, isFastTestCompleted, isFullTestCompleted, isAddTestCompleted;
    static int arrayUserResult[];
    static int[] arrayOfNumQuestions;

    public static void setUserResult(int currentQuestion, int userResult){

        if (currentQuestion < indexOfQuestion){

        }else if (currentQuestion > indexOfQuestion){
            currentQuestion = indexOfQuestion;
        }

        arrayUserResult[currentQuestion] = userResult;

    }

    public static int getUserResult(){

        int sumOfQuestions = 0;
        int arrayLength = Questions.arrayUserResult.length;
        Random rnd = new Random();

        for (int countOfArray = 0; countOfArray < arrayLength; countOfArray++){
            int newRandomNumber = rnd.nextInt(100);
            sumOfQuestions = sumOfQuestions + newRandomNumber;
//            switch (Questions.arrayUserResult[countOfArray]){
//                case 1: sumOfQuestions = sumOfQuestions + 7; break;
//                case 2: sumOfQuestions = sumOfQuestions + 6; break;
//                case 3: sumOfQuestions = sumOfQuestions + 5; break;
//                case 0: sumOfQuestions = sumOfQuestions + 0; break;
//                default: sumOfQuestions = sumOfQuestions + 0; break;
//            }
        }

        if (arrayLength == 0) arrayLength = 1;

        int resultOfTest = sumOfQuestions / arrayLength;

        return resultOfTest;
    }


    public static void setNumberOfQuestions(int currentQuestion, int userResult){

        if (currentQuestion < indexOfQuestion){

        }else if (currentQuestion > indexOfQuestion){
            currentQuestion = indexOfQuestion;
        }

        arrayUserResult[currentQuestion] = userResult;

    }



    public static void fillArrayOfNumQuestions(int lengthOfArray) {

        arrayOfNumQuestions = new int[lengthOfArray];
        for (int countOfArray = 0; countOfArray < lengthOfArray; countOfArray++) {
            arrayOfNumQuestions[countOfArray] = countOfArray + 1;
        }
        shuffleIntArray(arrayOfNumQuestions);
    }

    static void shuffleIntArray(int[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }



}
