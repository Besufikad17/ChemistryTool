def is_upper_case(c):
    if 65 <= ord(c) <= 90:
        return True
    else:
        return False


def is_lower_case(c):
    if 97 <= ord(c) <= 122:
        return True
    else:
        return False


def is_digit(c):
    if 48 <= ord(c) <= 57:
        return True
    else:
        return False


def is_letter(c):
    if is_lower_case(c) or is_upper_case(c):
        return True
    else:
        return False


def is_letter_or_digit(c):
    if is_digit(c) or is_letter(c):
        return True
    else:
        return False



