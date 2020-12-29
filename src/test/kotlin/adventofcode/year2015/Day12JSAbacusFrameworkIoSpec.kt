package adventofcode.year2015

import adventofcode.PuzzleBaseSpec

class Day12JSAbacusFrameworkIoSpec : PuzzleBaseSpec(
    listOf(
        """[1,2,3]""" to 6,
        """{"a":2,"b":4}""" to 6,
        """[[[3]]]""" to 3,
        """{"a":{"b":4},"c":-1}""" to 3,
        """{"a":[-1,1]}""" to 0,
        """[-1,{"a":1}]""" to 0,
        """[]""" to 0,
        """{}""" to 0
    ),
    listOf(
        """[1,2,3]""" to 6,
        """[1,{"c":"red","b":2},3]""" to 4,
        """{"d":"red","e":[1,2,3,4],"f":5}""" to 0,
        """[1,"red",5]""" to 6
    )
)
