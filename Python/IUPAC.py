from util import Constants, Character


class IUPAC:

    """ IUPAC class contains a function used to name organic compounds.
        Example: IUPAC i = new IUPAC("CH4");
                 i.getIUPACName();   . . . . . . methane """

    def __init__(self, given: str):
        self.given = given

    def get_iupac_name(self):

        """ Returns IUPAC Name of organic compound by determining the group of the compound.
            Example: IUPAC i = new IUPAC("CH4");
                     i.getIUPACName();   . . . . . . number of hydrogen = number of carbon * 2 + 2 -> Alkane
                     CH4 -> Methane """

        number_of_hydrogen = self.get_number_of_hydrogen()
        number_of_carbon = self.given.count('C')
        if number_of_hydrogen == number_of_carbon * 2 + 2:
            return Constants.prefix[number_of_carbon - 1] + 'ane'
        elif number_of_hydrogen == 2 * number_of_carbon:
            return Constants.prefix[number_of_carbon - 1] + 'ene'
        elif number_of_hydrogen == number_of_carbon * 2 - 2:
            return Constants.prefix[number_of_carbon - 1] + 'yne'

    def get_number_of_hydrogen(self):

        """ Returns the number of hydrogen in a given organic compound."""

        result = 0
        for i in range(0, len(self.given)):
            if Character.is_digit(self.given[i]):
                result = result + (ord(self.given[i]) - 48)
        return result
