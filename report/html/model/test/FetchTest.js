var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":120,"id":1275,"methods":[{"el":65,"sc":2,"sl":42},{"el":73,"sc":2,"sl":67},{"el":81,"sc":2,"sl":75},{"el":89,"sc":2,"sl":83},{"el":99,"sc":2,"sl":91},{"el":109,"sc":2,"sl":101},{"el":119,"sc":2,"sl":111}],"name":"FetchTest","sl":30}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_1":{"methods":[{"sl":75}],"name":"JumpTest","pass":true,"statements":[{"sl":77},{"sl":78},{"sl":79},{"sl":80}]},"test_10":{"methods":[{"sl":67}],"name":"BasicFetchWorks","pass":true,"statements":[{"sl":69},{"sl":70},{"sl":71},{"sl":72}]},"test_14":{"methods":[{"sl":101}],"name":"BEQTest","pass":true,"statements":[{"sl":103},{"sl":104},{"sl":105},{"sl":106},{"sl":107},{"sl":108}]},"test_3":{"methods":[{"sl":111}],"name":"BEQZTest","pass":true,"statements":[{"sl":113},{"sl":114},{"sl":115},{"sl":116},{"sl":117},{"sl":118}]},"test_5":{"methods":[{"sl":91}],"name":"BNETest","pass":true,"statements":[{"sl":93},{"sl":94},{"sl":95},{"sl":96},{"sl":97},{"sl":98}]},"test_7":{"methods":[{"sl":83}],"name":"JRTest","pass":true,"statements":[{"sl":85},{"sl":86},{"sl":87},{"sl":88}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [10], [], [10], [10], [10], [10], [], [], [1], [], [1], [1], [1], [1], [], [], [7], [], [7], [7], [7], [7], [], [], [5], [], [5], [5], [5], [5], [5], [5], [], [], [14], [], [14], [14], [14], [14], [14], [14], [], [], [3], [], [3], [3], [3], [3], [3], [3], [], []]
