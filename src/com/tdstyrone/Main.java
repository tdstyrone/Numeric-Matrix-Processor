package com.tdstyrone;
import java.util.Scanner;

public class Main {
    
    public Main(){}

    private static int matrixMenu(Scanner scanner){
        System.out.println("1. Add Matrices");
        System.out.println("2. Multiply Matrix to a constant");
        System.out.println("3. Multiply Matrices");
        System.out.println("4. Transpose Matrix");
        System.out.println("5. Calculate Determinant");
        System.out.println("6. Inverse Matrix");
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

    private static int[][] transposeMatrix(int[][] matrix){
        int[][] transpose = new int[matrix[0].length][matrix.length];

        for(int i = 0; i < matrix[0].length; i++){
            for(int j = 0; j < matrix.length; j++){
                transpose[i][j] = matrix[j][i];
            }
        }
        return transpose;
    }

    private static Integer calculateDet(int[][] matrix){
        int determinant = 0;
        if(matrix.length == matrix[0].length){
            if(matrix.length == 2){
                determinant = ((matrix[0][0]*matrix[1][1])- matrix[0][1]*matrix[1][0]);
            }
            else{
                for(int i = 0; i < matrix.length; i++){
                    if (i % 2 == 0){
                        determinant += matrix[0][i] * calculateDet(matrixMinor(matrix, 0, i));
                    }
                    else{
                        determinant -= matrix[0][i] * calculateDet(matrixMinor(matrix, 0, i));
                    }
                }
            }
            return determinant;
        }
        else {
            return null;
        }
    }

    private static int[][] matrixMinor(int[][] matrix, int row, int col){
        int[][] tempMatrix = new int[matrix.length-1][matrix[0].length-1];
        int tempRow = 0;
        int tempCol = 0;

        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                if( i != row && j != col){
                    tempMatrix[tempRow][tempCol] = matrix[i][j];
                    tempCol++;
                }
            }
            tempCol = 0;
            if (i != row)
                tempRow++;
        }
        return tempMatrix;
    }

    private static void invertMatrix(int[][] matrix){
        Double determinant = Double.valueOf(calculateDet(matrix));

        if (determinant != 0){
            int[][] tempMatrix = transposeMatrix(matrixCofactor(matrix));
            Double[][] inverse = new Double[matrix.length][matrix.length];
            for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix[0].length; j++){
                    inverse[i][j] = (1/determinant) * tempMatrix[i][j];
                }
            }
            System.out.println("The Inverse of the matrix is: ");
            for(Double[] row : inverse) {
                for (int column = 0; column < row.length; column++) {
                    System.out.printf("%.2f ", row[column]);
                }
                System.out.println();
            }
        }
        else{
            System.out.println("ERROR");
        }
    }

    private static int[][] matrixCofactor(int[][] matrix){
        int[][] cofactor = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if ((i+j)%2 == 0) {
                    cofactor[i][j] = calculateDet(matrixMinor(matrix,i,j));
                }
                else {
                    cofactor[i][j] = -1*(calculateDet(matrixMinor(matrix,i,j)));
                }
            }
        }
        return cofactor;
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
            case 4:
                System.out.println("The transpose matrix result is: ");
                break;
            case 5:
                System.out.println("The Determinant of the matrix is: ");
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
                        System.out.print("Enter scalar constant: ");
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
                case 4:
                    try {
                        printMatrix(transposeMatrix(inputMatrix(scanner)), choice);
                    }
                    catch(NullPointerException e){
                        System.out.println("ERROR");
                    }
                    break;
                case 5:
                    try{
                        System.out.println("The Determinant is: " + calculateDet(inputMatrix(scanner)));
                    }
                    catch(NullPointerException e){
                        System.out.println("ERROR");
                    }
                    break;
                case 6:
                    try{
                        invertMatrix(inputMatrix(scanner));
                    }
                    catch(NullPointerException e){
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
