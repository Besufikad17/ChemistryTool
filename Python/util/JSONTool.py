import json


def decode_json(i):

    """parses the i - 1 element of JSONArray to python dictionary."""

    opps = []
    with open('JSON/PeriodicTableJSON.json', 'r') as json_file:
        data = json.load(json_file)
        for p in data['elements']:
            opps.append(p)
        return opps[i - 1]