package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Thomas on 9/9/2017.
 */

public class Cipher_Checker {

    static char[][] cipher =
            {{'N', 'N', 'N'},
                    {'N', 'N', 'N'},
                    {'N', 'N', 'N'},
                    {'N', 'N', 'N'}};

    static char[][][] solutions =
            {{{'W', 'B', 'W'},
                    {'B', 'W', 'B'},
                    {'W', 'B', 'W'},
                    {'B', 'W', 'B'}},

                    {{'B', 'W', 'B'},
                            {'W', 'B', 'W'},
                            {'B', 'W', 'B'},
                            {'W', 'B', 'W'}},

                    {{'W', 'B', 'W'},
                            {'B', 'W', 'B'},
                            {'B', 'W', 'B'},
                            {'W', 'B', 'W'}},

                    {{'B', 'W', 'B'},
                            {'W', 'B', 'W'},
                            {'W', 'B', 'W'},
                            {'B', 'W', 'B'}},

                    {{'B', 'W', 'W'},
                            {'B', 'B', 'W'},
                            {'W', 'B', 'B'},
                            {'W', 'W', 'B'}},

                    {{'W', 'B', 'B'},
                            {'W', 'W', 'B'},
                            {'B', 'W', 'W'},
                            {'B', 'B', 'W'}}};

    static boolean isPossible[] = {true, false, true, true, true, true};

    public static void CheckCipher() {

        for (int l = 0; l < isPossible.length; l++) {

            isPossible[l] = true;

        }

        for (int i = 0; i < 6; i++) {

            for (int j = 0; j < 4; j++) {

                for (int k = 0; k < 3; k++) {

                    if (cipher[j][k] != 'N') {

                        if (cipher[j][k] != solutions[i][j][k]) {

                            isPossible[i] = false;

                        }

                    }

                }

            }

        }

        for (int i = 0; i < isPossible.length; i++) {

            if (isPossible[i])
                System.out.println("Solution " + (i + 1) + " is possible.");

        }

    }


}
