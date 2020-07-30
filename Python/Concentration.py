from util.Parser import *


class Concentration:

    """Concentration class contains functions to calculate unknown concentration of substance.
       Example:  concentration = Concentration("10kg","12millilitre","CH4")
                 concentration.calculate_molarity()   . . . .  Molarity = given mass / (Molar mass * volume of solution) """

    def __init__(self,given1,given2,given3 = None):
        self.given1 = given1
        self.given2 = given2
        self.given3 = given3
        self.molarity = 0
        self.molality = 0
        self.normality = 0
        self.mass = 0
        self.mole = 0
        self.volume = 0
        self.molar_mass = 0
        self.mass_of_solute = 0
        self.mass_of_solvent = 0
        self.number_of_equivalent = 0
        self.equivalent_mass = 0
        self.givens = [self.given1,self.given2,self.given3]

    def calculate_molarity(self):

        """ Returns value that express unknown concentration of a substance in terms of molarity."""

        for given in self.givens:
            if given is None:
                pass
            else:
                if is_mass(given):
                    self.mass = get_value(given)
                    if given.endswith('kg'):
                        self.mass = self.mass * 1000
                elif is_mole(given):
                    self.mole = get_value(given)
                elif is_volume(given):
                    self.volume = get_value(given)
                    if given.endswith('millilitre'):
                        self.volume = self.volume / 1000
                else:
                    self.molar_mass = calculate_molar_mass(get_elements(given), given)
        if self.mass == 0 and self.molar_mass == 0:
            self.molarity = self.mole/self.volume
        elif self.mole == 0:
            self.molarity = self.mass/self.volume * self.molar_mass
        else:
            print('Missing Input')
        return self.molarity

    def calculate_molality(self):

        """ Returns value that express unknown concentration of a substance in terms of molality."""

        for given in self.givens:
            if given is None:
                pass
            else:
                if is_mass(given):
                    self.mass_of_solute = get_value(given)
                    if given.endswith('g'):
                        self.mass = self.mass / 1000
                elif given.endswith('solvent'):
                    self.mass_of_solvent = get_value(given)
                elif is_mole(given):
                    self.mole = get_value(given)
                else:
                    self.molar_mass = calculate_molar_mass(get_elements(given), given)
        if self.mass_of_solute == 0 and self.molar_mass == 0:
            self.molality = self.mole/self.mass_of_solvent
        elif self.mole == 0:
            self.molality = self.mass_of_solute / (self.molar_mass * self.mass_of_solvent)
        else:
            print('Missing Input')
        return self.molality

    def calculate_normality(self):

        """Returns value that express unknown concentration of a substance in terms of normality."""

        for given in self.givens:
            if given is None:
                pass
            else:
                if is_volume(given):
                    self.volume = get_value(given)
                    if given.endswith('millilitre'):
                        self.volume = self.volume / 1000
                elif is_number_of_equivalent_mass(given):
                    self.number_of_equivalent = get_value(given)
                elif is_mass(given):
                    self.mass = get_value(given)
                else:
                    self.equivalent_mass = calculate_equivalent_mass(given)

        if self.mass == 0 and self.equivalent_mass == 0:
            self.normality = self.number_of_equivalent/self.volume
        elif self.number_of_equivalent == 0:
            self.normality = self.mass / self.equivalent_mass * self.volume
        else:
            print('Missing Input')
        return self.normality

    def calculate_percentage_composition(self):

        """ Calculates amount of element in a compound. """

        molar_mass = calculate_molar_mass(get_elements(self.given1),self.given1)
        molar_mass2 = calculate_molar_mass(get_elements(self.given2),self.given2)
        return "There is " + str((molar_mass/molar_mass2) * 100) + " % " + self.given1 + " in " + self.given2









