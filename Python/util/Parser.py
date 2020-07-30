from . import Character, Constants


def is_pressure(given: str):

    """Checks whether a given quantity is pressure or not."""

    if given.endswith('atm') or given.endswith('pas'):
        return True
    else:
        return False


def is_volume(given: str):

    """Checks whether a given quantity is volume or not."""

    if given.endswith('millilitre') or given.endswith('litre'):
        return True
    else:
        return False


def is_temprature(given: str):

    """Checks whether a given quantity is tempreture or not."""

    if given.endswith('K') or given.endswith('C'):
        return True
    else:
        return False


def is_mole(given: str):

    """Checks whether a given quantity is amount of substance or not."""

    return given.endswith('mol')


def is_mass(given: str):

    """Checks whether a given quantity is mass or not."""

    if given.endswith('kg') or given.endswith('g'):
        return True
    else:
        return False


def is_number_of_equivalent_mass(given: str):

    """Checks whether a given quantity is number of equivalent mass or not."""

    if given.endswith('kg/eq') or given.endswith('g/eq'):
        return True
    else:
        return False


def is_acid(given: str):

    """Checks whether a given compound is acid or not."""

    try:
        if Constants.acids.index(given) > 0:
            return True
    except ValueError:
        return False


def is_base(given: str):

    """Checks whether a given compound is base or not."""

    try:
        if Constants.bases.index(given) > 0:
            return True
    except ValueError:
        return False


def get_unit(given: str):

    """ Returns the unit of a given quantity."""

    if given is None:
        pass
    else:
        if is_pressure(given):
            if given.endswith('atm'):
                return 'atm'
            else:
                return 'pas'
        elif is_volume(given):
            if given.endswith('ml'):
                return 'ml'
            else:
                return 'l'
        elif is_temprature(given):
            if given.endswith('K'):
                return 'K'
            else:
                return 'C'
        elif is_mole(given):
            return 'mol'
        elif is_mass(given):
            if given.endswith('kg'):
                return 'kg'
            else:
                return 'g'
        else:
            pass



def parse(givens: list):

    """ Parses user inputs to known physical quantities for calculating unknown quantities by using gas laws(Boyle's , Charle's and Combined).
        Example: parse(['p1 = 12atm','p2 = 24atm','v1 = 40millilitre']) . . . . [12,24,40,0,0,0]
    """

    result = [0, 0, 0, 0, 0, 0]
    for given in givens:
        if given is None:
            pass
        else:
            unit = get_unit(given)
            value = float(given[given.index('=') + 1: given.index(unit)])
            if given.startswith('p1'):
                result[0] = value
            elif given.startswith('p2'):
                result[1] = value
            elif given.startswith('v1'):
                result[2] = value
            elif given.startswith('v2'):
                result[3] = value
            elif given.startswith('T1'):
                result[4] = value
            elif given.startswith('T2'):
                result[5] = value
            else:
                pass

    return result


def parser(givens: list, is_ideal: bool):

    """Parses user inputs to known physical quantities for calculating unknown quantities by using ideal gas law.
       Example: parser(['p = 10atm','v = 12millilitre','n = 2mole'],True) . . . . . . [10,12,0,2,0.082]
    """

    result = [0, 0, 0, 0, 0]
    R = 0
    if is_ideal:
        for given in givens:
            unit = get_unit(given)
            value = float(given[given.index('=') + 1: given.index(unit)])
            if given.startswith('p'):
                result[0] = value
                if unit == 'atm':
                    R = 0.082
                else:
                    R = 8.314
            elif given.startswith('v'):
                result[1] = value
            elif given.startswith('T'):
                result[2] = value
            elif given.startswith('n'):
                result[3] = value
            else:
                print('Invalid Input')
        result[4] = R
    return result


def get_elements(compound: str):

    """ Returns list of elements in a compound.
        Example: get_elements'NaCl') . . . . . . ['Na','Cl']
    """

    elements = []
    for i in range(0, len(compound)):
        if Character.is_upper_case(compound[i]):
            if i != len(compound) - 1:
                if Character.is_upper_case(compound[i + 1]):
                    if i + 1 != len(compound) - 1:
                        if Character.is_lower_case(compound[i + 2]):
                            elements.append(compound[i])
                            elements.append(str(compound[i + 1] + compound[i + 2]))
                        else:
                            elements.append(compound[i])
                            elements.append(compound[i + 1])
                    else:
                        elements.append(compound[i])
                        elements.append(compound[i + 1])
                elif i == 0:
                    if Character.is_lower_case(compound[i + 1]):
                        elements.append(str(compound[i] + compound[i + 1]))
                    else:
                        elements.append(compound[i])
                elif Character.is_lower_case(compound[i + 1]):
                    elements.append(str(compound[i] + compound[i + 1]))
            else:
                elements.append(compound)
    remove_duplicate_elements(elements)
    return elements


def remove_duplicate_elements(l: list):

    """ Removes duplicate element from list."""

    for item in l:
        if l.count(item) > 1:
            l.remove(item)
        else:
            pass


def calculate_molar_mass(elements: list, compound: str):

    """ Calculates molar mass of a given compound."""

    molar_mass = 0
    if len(elements) == 1:
        molar_mass = Constants.atomic_mass.get(elements[0])
    else:
        for i in range(0, len(elements)):
            index = compound.index(elements[i])
            atomic_mass = Constants.atomic_mass.get(elements[i])
            is_letter = Character.is_letter(compound[index + 1])
            v = atomic_mass * (ord(compound[index + 1]) - 48)
            if index == 0:
                if is_letter:
                    molar_mass = molar_mass + atomic_mass
                else:
                    molar_mass = molar_mass + v
            else:
                if index == len(compound) - 1:
                    if Character.is_letter(compound[0]):
                        molar_mass = molar_mass + atomic_mass
                    else:
                        molar_mass = molar_mass + (atomic_mass * (ord(compound[0]) - 48))
                else:
                    if Character.is_letter(compound[0]):
                        molar_mass = molar_mass + v
                    else:
                        molar_mass = molar_mass + (v * (ord(compound[0]) - 48))
    return molar_mass


def get_value(given: str):

    """Extracts numerical value from a given user input."""

    index = 0
    for i in range(0, len(given)):
        if Character.is_letter(given[i]):
            index = i
            break
        else:
            pass
    return float(given[0:index])


def to_string(char_arr: list):

    """ Converts a given character array(list) to string."""

    string = ''
    for i in range(0, len(char_arr)):
        string = string + char_arr[i]
    return string


def to_char_array(given: str):

    """Converts a given string to character array(list)."""

    char_arr = []
    for j in range(0, len(given)):
        char_arr.append(given[j])
    char_arr.remove(')')
    char_arr.remove('(')
    return char_arr


def parse_base(given: str):
    if given.find(')') > 0:
        value = int(given[given.index(')') + 1])
        given.replace('(', '')
        given.replace(')', '')
        for i in range(1, value):
            given = given + 'OH'
    return given


def calculate_equivalent_mass(given: str):

    """Calculates equivalent mass of a given compound."""

    if is_acid(given):
        molar_mass = calculate_molar_mass(get_elements(given), given)
        return molar_mass / given.count('H')
    elif is_base(given):
        if given.count(')') > 0:
            string = to_string(to_char_array(parse_base(given)))
            k = string.count('OH')
            molar_mass = calculate_molar_mass2(k, get_elements(given))
            return molar_mass / k
        else:
            k = given.count('OH')
            molar_mass = calculate_molar_mass2(k, get_elements(given))
            return molar_mass / k


def calculate_molar_mass2(k: int, l: list):

    """Calculates molar mass of basic compound with more than 1 hydroxide ion."""
    
    molar_mass = 0
    for i in range(0, len(l)):
        if l[i] == 'O' or l[i] == 'H':
            atomic_mass = k * (Constants.atomic_mass.get(l[i]))
            molar_mass = molar_mass + atomic_mass
        else:
            atomic_mass = Constants.atomic_mass.get(l[i])
            molar_mass = molar_mass + atomic_mass
    return molar_mass
