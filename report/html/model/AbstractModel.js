var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":42,"id":2798,"methods":[{"el":25,"sc":2,"sl":20},{"el":33,"sc":2,"sl":31},{"el":41,"sc":2,"sl":39}],"name":"AbstractModel","sl":15}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_1":{"methods":[{"sl":20}],"name":"JumpTest","pass":true,"statements":[{"sl":21}]},"test_14":{"methods":[{"sl":20}],"name":"BEQTest","pass":true,"statements":[{"sl":21}]},"test_17":{"methods":[{"sl":20}],"name":"ALUListProcessesCorrectly","pass":true,"statements":[{"sl":21}]},"test_2":{"methods":[{"sl":20}],"name":"EncodeTest","pass":true,"statements":[{"sl":21}]},"test_20":{"methods":[{"sl":20}],"name":"MemoryAccessProcessesCorrectly","pass":true,"statements":[{"sl":21}]},"test_21":{"methods":[{"sl":20}],"name":"getFirstALUTest","pass":true,"statements":[{"sl":21}]},"test_3":{"methods":[{"sl":20}],"name":"BEQZTest","pass":true,"statements":[{"sl":21}]},"test_4":{"methods":[{"sl":20}],"name":"DecodeTest","pass":true,"statements":[{"sl":21}]},"test_5":{"methods":[{"sl":20}],"name":"BNETest","pass":true,"statements":[{"sl":21}]},"test_7":{"methods":[{"sl":20}],"name":"JRTest","pass":true,"statements":[{"sl":21}]},"test_9":{"methods":[{"sl":20}],"name":"issueTest","pass":true,"statements":[{"sl":21}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [3, 17, 21, 2, 14, 20, 5, 7, 9, 4, 1], [3, 17, 21, 2, 14, 20, 5, 7, 9, 4, 1], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], []]
