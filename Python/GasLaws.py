from util.Parser import *
import math


class GasLaws:

    def __init__(self,given1,given2,given3 = None,given4 = None,given5 = None):

        """ GasLaws class contains functions to calculate unknown quantities of a system by using formulas listed in Gas Laws.
            Example :  boyle = GasLaws(given1 : "p1 = 10atm",given2 : "p2 = 40atm",given3 : "v2 = 12litre")
                       boyle.calculate_boyles();  . . . . . . .  v1 = (p2 * v2) / p1  = 48litre
        """

        self.given1 = given1
        self.given2 = given2
        self.given3 = given3
        self.given4 = given4
        self.given5 = given5

    def calculate_boyles(self):

        """ Calculates unknown quantities of a system when temperature kept constant """

        values = parse([self.given1,self.given2,self.given3])
        for i in range(0,4):
            if values[i] == 0:
                if i == 0:
                    return (values[1] * values[3]) / values[2]
                elif i == 1:
                    return (values[0] * values[2])/values[3]
                elif i == 2:
                    return (values[1] * values[3])/values[0]
                else:
                    return (values[0] * values[2])/values[1]

    def calculate_charles(self):

        """ Calculates unknown quantities of a system when pressure kept constant """

        values = parse([self.given1, self.given2, self.given3])
        for i in range(2,6):
            if values[i] == 0:
                if i == 2:
                    return (values[3] * values[4]) / values[5]
                elif i == 3:
                    return (values[2] * values[5])/values[4]
                elif i == 4:
                    return (values[2] * values[5])/values[3]
                else:
                    return (values[3] * values[4])/values[2]

    def calculate_combined(self):

        """ Calculates unknown quantities of a system when amount of substance(mole) kept constant """

        values2 = parse([self.given1,self.given2,self.given3,self.given4,self.given5])
        for i in range(0,6):
            if values2[i] == 0:
                if i == 0:
                    return (values2[1] * values2[3] * values2[4])/(values2[2] * values2[5])
                elif i == 1:
                    return (values2[0] * values2[2] * values2[5])/(values2[3] * values2[4])
                elif i == 2:
                    return (values2[1] * values2[3] * values2[4])/(values2[0] * values2[5])
                elif i == 3:
                    return (values2[0] * values2[2] * values2[5])/(values2[1] * values2[4])
                elif i == 4:
                    return (values2[0] * values2[2] * values2[5])/(values2[1] * values2[3])
                else:
                    return (values2[1] * values2[3] * values2[4])/(values2[0] * values2[2])

    def calculate_ideal(self):

        """ Calculates unknown quantities of a system by using the relationship between temperature ,
            volume , pressure and amount of substance."""

        value = parser([self.given1, self.given2, self.given3], True)
        for i in range(0,4):
            if value[i] == 0:
                if i == 0:
                    return (value[3] * value[4] * value[2])/value[1]
                elif i == 1:
                    return (value[3] * value[4] * value[2])/value[0]
                elif i == 2:
                    return (value[0] * value[1] * value[3])/value[4]
                else:
                    return (value[0] * value[1] * value[2])/value[4]

    def calculate_grhams(self):

        """ Calculates the diffusion rate of one substance compare to another."""

        molar_mass1 = calculate_molar_mass(get_elements(self.given1),self.given1)
        molar_mass2 = calculate_molar_mass(get_elements(self.given2),self.given2)
        return self.given1 + " diffuses " + str(math.sqrt(molar_mass2/molar_mass1)) + " times than " + self.given2



