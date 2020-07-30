from util import JSONTool


class Element:

    """ Element class contains data fields that give detail information about a given element.
        Example: hydrogen = Element(atomic_number: 1)
                 hydrogen.atomic_number . . . . . 1
                 hydrogen.boiling_point . . . . . 20.271
                 hydrogen.density . . . . . . . . 0.08988
                 hydrogen.melting_point . . . . . 13.99
                 hydrogen.electronic_configuration . 1s1
                 hydrogen.discovered_by . . . . . . Henry Cavendish"""

    def __init__(self, atomic_number:int):
        self.symbol = JSONTool.decode_json(atomic_number).get('symbol')
        self.atomic_number = atomic_number
        self.discovered_by = JSONTool.decode_json(atomic_number).get('discovered_by')
        self.mass_number = JSONTool.decode_json(atomic_number).get('atomic_mass')
        self.electronic_configuration = JSONTool.decode_json(atomic_number).get('electron_configuration')
        self.density = JSONTool.decode_json(atomic_number).get('density')
        self.melting_point = JSONTool.decode_json(atomic_number).get('melt')
        self.boiling_point = JSONTool.decode_json(atomic_number).get('boil')

