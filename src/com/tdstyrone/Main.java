package com.tdstyrone;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static int matrixMenu(Scanner scanner){
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix to a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("0. Exit");
        System.out.print("Your Choice: ");
        return scanner.nextInt();
    }

    private static int[][] inputMatrix(Scanner scanner){
        System.out.println("Please enter the dimensions of the Matrix: ");
        int matrixRow = scanner.nextInt();
        int matrixCol = scanner.nextInt();
        int[][] matrix = new int[matrixRow][matrixCol];
        if(matrixRow <= 0 || matrixCol <= 0) { return null;}
        else{
            System.out.println("Enter Values for Matrix: ");
            for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix[0].length; j++){
                    matrix[i][j] = scanner.nextInt();
                }
            }
            return matrix;
        }
    }

    private static int[][] addMatrix(int[][] matrixA, int[][] matrixB){
        if(matrixA.length == matrixB.length && matrixA[0].length == matrixB[0].length){
            int[][] sumMatrix = new int[matrixA.length][matrixA[0].length];
            for(int i = 0; i < matrixA.length; i++){
                for(int j = 0; j < matrixA[0].length; j++){
                    sumMatrix[i][j] = matrixA[i][j] + matrixB[i][j];
                }
            }
            return sumMatrix;
        }
        else{
            return null;
        }
    }

    private static int[][] scaleMatrix(int[][] matrix, int constant){
        int[][] scaledMatrix = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                scaledMatrix[i][j] = constant * matrix[i][j];
            }
        }
        return scaledMatrix;
    }

    private static int[][] multiplyMatrix(int[][] matrixA, int[][] matrixB){
        if(matrixA[0].length == matrixB.length){
            int[][] productMatrix = new int[matrixA.length][matrixB[0].length];
            for (int i = 0; i < matrixA.length; i++){
                for(int j = 0; j < matrixB[0].length; j++){
                    for (int k = 0; k < matrixA[0].length; k++) {
                        productMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                    }
                }
            }
            return productMatrix;
        }
        else {
            return null;
        }
    }

    private static void printMatrix(int[][] matrix, int choice){

        switch (choice){
            case 1:
                System.out.println("The addition result is: ");
                break;
            case 2:
                System.out.println("The scalar result is: ");
                break;
            case 3:
                System.out.println("The multiplication result is: ");
                break;
            default:
                break;
        }
        for(int[] row : matrix){
            for(int column = 0; column < row.length; column++){
                System.out.print(row[column] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an Option Below:");
        int choice = matrixMenu(scanner);
        while (choice != 0){
            switch (choice){
                case 1:
                    try {
                        printMatrix(addMatrix(inputMatrix(scanner), inputMatrix(scanner)), choice);
                    }
                    catch(NullPointerException e){
                        System.out.println("ERROR");
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Enter scalar constant: ");
                        int constant = scanner.nextInt();
                        printMatrix(scaleMatrix(inputMatrix(scanner), constant), choice);
                    }
                    catch(NullPointerException e){
                        System.out.println("ERROR");
                    }
                    break;
                case 3:
                    try {
                        printMatrix(multiplyMatrix(inputMatrix(scanner), inputMatrix(scanner)), choice);
                    }
                    catch (NullPointerException e){
                        System.out.println("ERROR");
                    }
                    break;
                default:
                    System.out.println("Please Enter a valid option.");
                    break;
            }
            System.out.println();
            choice = matrixMenu(scanner);
        }
        System.out.println("THANK YOU FOR USING");
    }
}
