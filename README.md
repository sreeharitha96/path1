# path1
 
 Spring boot api
 
 http://localhost:8090/path/swagger-ui/index.html#/path-rest-controller/generatePathsUsingPOST
 
 Json format
 
 input : 
 {
  "numOfCabs": 4,
  "numPerCar": 5,
  "source": {"name":"snell","x":0,"y":0},
  "vertices": [
{"name":"a","x":1,"y":1},{"name":"b","x":3,"y":5},{"name":"c","x":2,"y":4},{"name":"d","x":-1,"y":-5},{"name":"e","x":0,"y":7},{"name":"f","x":-3,"y":-5},{"name":"g","x":2,"y":8},{"name":"h","x":-2,"y":-6},{"name":"i","x":2,"y":9},{"name":"j","x":2,"y":0},{"name":"k","x":0,"y":2},{"name":"l","x":-4,"y":-2},{"name":"m","x":4,"y":7},{"name":"n","x":-6,"y":-2},{"name":"o","x":4,"y":8},{"name":"p","x":3,"y":0},{"name":"q","x":0,"y":3},{"name":"r","x":-5,"y":-4},{"name":"s","x":2,"y":2},{"name":"t","x":-4,"y":-4},{"name":"u","x":2,"y":7},{"name":"v","x":7,"y":0},{"name":"x","x":0,"y":4},{"name":"y","x":-7,"y":-1},{"name":"z","x":1,"y":3}
  ]
}

output : 
[
  [
    {
      "name": "snell",
      "x": 0,
      "y": 0
    },
    {
      "name": "a",
      "x": 1,
      "y": 1
    },
    {
      "name": "s",
      "x": 2,
      "y": 2
    },
    {
      "name": "z",
      "x": 1,
      "y": 3
    },
    {
      "name": "q",
      "x": 0,
      "y": 3
    },
    {
      "name": "k",
      "x": 0,
      "y": 2
    },
    {
      "name": "snell",
      "x": 0,
      "y": 0
    }
  ],
  [
    {
      "name": "snell",
      "x": 0,
      "y": 0
    },
    {
      "name": "p",
      "x": 3,
      "y": 0
    },
    {
      "name": "c",
      "x": 2,
      "y": 4
    },
    {
      "name": "b",
      "x": 3,
      "y": 5
    },
    {
      "name": "o",
      "x": 4,
      "y": 8
    },
    {
      "name": "x",
      "x": 0,
      "y": 4
    },
    {
      "name": "snell",
      "x": 0,
      "y": 0
    }
  ],
  [
    {
      "name": "snell",
      "x": 0,
      "y": 0
    },
    {
      "name": "e",
      "x": 0,
      "y": 7
    },
    {
      "name": "u",
      "x": 2,
      "y": 7
    },
    {
      "name": "g",
      "x": 2,
      "y": 8
    },
    {
      "name": "i",
      "x": 2,
      "y": 9
    },
    {
      "name": "m",
      "x": 4,
      "y": 7
    },
    {
      "name": "snell",
      "x": 0,
      "y": 0
    }
  ],
  [
    {
      "name": "snell",
      "x": 0,
      "y": 0
    },
    {
      "name": "y",
      "x": -7,
      "y": -1
    },
    {
      "name": "n",
      "x": -6,
      "y": -2
    },
    {
      "name": "l",
      "x": -4,
      "y": -2
    },
    {
      "name": "v",
      "x": 7,
      "y": 0
    },
    {
      "name": "j",
      "x": 2,
      "y": 0
    },
    {
      "name": "snell",
      "x": 0,
      "y": 0
    }
  ]
]
