package com.iast.astbenchmark.cases;

import com.iast.astbenchmark.cases.bean.SourceTestObject;
import com.iast.astbenchmark.cases.bean.SourceTestWith100Filedsbject;
import com.iast.astbenchmark.cases.bean.SourceTestWith10Filedsbject;
import com.iast.astbenchmark.cases.bean.SourceTestWithMPObject;
import com.iast.astbenchmark.cases.bean.SoureWithQueueBean;
import com.iast.astbenchmark.cases.bean.SoureWithSetBean;
import com.iast.astbenchmark.cases.bean.layers.LayerBaseBean2;
import com.iast.astbenchmark.cases.bean.layers.LayerBaseBean3;
import com.iast.astbenchmark.cases.bean.layers.LayerBaseBean9;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

import static com.iast.astbenchmark.common.CommonConsts.ERROR_STR;
import static com.iast.astbenchmark.common.CommonConsts.SUCCESS_STR;

/**
 * 基础跟踪能力->污点对象完整度（aTaintCase001～case0021）
 */
@RestController()
public class AstTaintCase001 {
    /**
     * 字符串对象,String
     * @param cmd
     * @return
     */
    @PostMapping ("case00901")
    public Map<String, Object> aTaintCase00901(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 字符串对象,StringBuffer
     * @param cmd
     * @return
     */
    @PostMapping ("case00902")
    public Map<String, Object> aTaintCase00902(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuffer buffer = new StringBuffer(cmd);
            Runtime.getRuntime().exec(new String(buffer));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e)  {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }
    /**
     * 字符串对象,StringBuffer
     * @param cmd
     * @return
     */
    @PostMapping("case00903")
    public Map<String, Object> aTaintCase00903(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder buffer = new StringBuilder(cmd);
            Runtime.getRuntime().exec(new String(buffer));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e)  {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /** 污点对象完整度 基础类型 **/
    /**
     * aTaintCase001 基本类型int 作为污点对象
     *
     * @param cmd
     * @return
     */
    @GetMapping("case001/{cmd}")
    public Map<String, Object> aTaintCase001(@PathVariable int cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e)  {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }


    /**
     * aTaintCase002 基本类型char 作为污点对象
     * 测试数据传（0～9）
     *
     * @return
     */
    @GetMapping("case002/{cmd}")
    public Map<String, Object> aTaintCase002(@PathVariable char cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 基本类型byte 作为污点对象
     *
     * @param cmd
     * @return
     */
    @GetMapping("case003/{cmd}")
    public Map<String, Object> aTaintCase003(@PathVariable byte cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 基础类型long 作为污点对象
     *
     * @param cmd
     * @return
     */
    @GetMapping("case004/{cmd}")
    public Map<String, Object> aTaintCase004(@PathVariable long cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /** 污点对象完整度 2.引用类型 **/

    /**
     * 引用类型Map 作为污点对象
     *
     * @param cmd
     * @return
     */
    @PostMapping("case00927")
    public Map<String, Object> aTaintCase927(@RequestBody Map<String, String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.isEmpty()) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        PrintWriter printWriter = new PrintWriter(System.out);
        printWriter.print(cmd);
        // Runtime.getRuntime().exec(cmd));
        modelMap.put("status", SUCCESS_STR);
        return modelMap;
    }

    /**
     * 引用类型List 作为污点对象
     *
     * @param cmd
     * @return
     */
    @PostMapping("case00928")
    public Map<String, Object> aTaintCase00928(@RequestBody List<String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || CollectionUtils.isEmpty(cmd)) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        PrintWriter printWriter = new PrintWriter(System.out);
        printWriter.print(cmd);
        //Runtime.getRuntime().exec(cmd.get(0));
        modelMap.put("status", SUCCESS_STR);
        return modelMap;
    }

    /**
     * 引用类型queue 作为污点对象
     *
     * @param cmd
     * @return
     */
    @PostMapping("case00929")
    public Map<String, Object> aTaintCase00929(@RequestBody List<String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || CollectionUtils.isEmpty(cmd)) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        Queue<String> queue = new LinkedBlockingQueue();
        queue.add(cmd.get(0));
        PrintWriter printWriter = new PrintWriter(System.out);
        printWriter.print(queue);
        //Runtime.getRuntime().exec(queue.peek());
        modelMap.put("status", SUCCESS_STR);
        return modelMap;
    }

    /**
     * 引用类型Set 作为污点对象
     *
     * @param
     * @return
     */
    @Deprecated
    @PostMapping("case00930")
    public Map<String, Object> aTaintCase00930(@RequestBody List<String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || CollectionUtils.isEmpty(cmd)) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        Set<String> stringSet = new HashSet<>(cmd);
        PrintWriter printWriter = new PrintWriter(System.out);
        printWriter.print(stringSet);
        //Runtime.getRuntime().exec(cmd.get(stringSet.iterator().next()));
        modelMap.put("status", SUCCESS_STR);
        return modelMap;
    }

    @PostMapping("case005")
    public Map<String, Object> aTaintCase005(@RequestBody Map<String, String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.isEmpty()) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd.get(cmd.keySet().iterator().next()));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 引用类型List 作为污点对象
     *
     * @param cmd
     * @return
     */
    @PostMapping("case006")
    public Map<String, Object> aTaintCase006(@RequestBody List<String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || CollectionUtils.isEmpty(cmd)) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd.get(0));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 引用类型queue 作为污点对象
     *
     * @param
     * @return
     */
    @PostMapping("case007")
    public Map<String, Object> aTaintCase007(@RequestBody SoureWithQueueBean queueBean) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(queueBean.getQueue().peek());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 引用类型Set 作为污点对象
     *
     * @param
     * @return
     */
    @PostMapping("case008")
    public Map<String, Object> aTaintCase008(@RequestBody SoureWithSetBean setBean) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(setBean.getValue().iterator().next());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }



    /**
     * 基本数据类型的封装类型 Byte 作为污点对象
     *
     * @param cmd
     * @return
     */
    @PostMapping("case009/{cmd}")
    @Deprecated
    public Map<String, Object> aTaintCase009(@PathVariable Byte cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }


    /**
     * 基本数据类型的封装类型 Integer 作为污点对象
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0010/{cmd}")
    @Deprecated
    public Map<String, Object> aTaintCase0010(@PathVariable Integer cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }


    /**
     * 基本数据类型的封装类型 Long 作为污点对象
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0011/{cmd}")
    @Deprecated
    public Map<String, Object> aTaintCase0011(@PathVariable Long cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 基本数据类型的封装类型 Character 作为污点对象
     *
     * @param cmd 测试数据使用（0~9）
     * @return
     */
    @PostMapping("case0012/{cmd}")
    @Deprecated
    public Map<String, Object> aTaintCase0012(@PathVariable Character cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 数组 String[] 作为污点对象
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0013")
    public Map<String, Object> aTaintCase0013(@RequestBody String[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.length < 1) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 数组 char[] 作为污点对象
     *
     * @param cmd [1,2]
     * @return
     */
    @PostMapping("case0014")
    public Map<String, Object> aTaintCase0014(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
//        if (cmd == null || cmd.length < 1) {
//            modelMap.put("status", ERROR_STR);
//            return modelMap;
        //      }
        //char[] data = {(char) cmd[0], (char) cmd[1]};
       char[] data = cmd.toCharArray();
        try {
            //java.io.PrintWriter printWriter = new PrintWriter(System.out);
            //printWriter.print(data);
           Runtime.getRuntime().exec(new String(data));
            modelMap.put("status", SUCCESS_STR);
        } catch  (IOException e)  {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 数组 byte[] 作为污点对象
     *  TODO 没有直接传入的sink,先用这个
     * @param cmd
     * @return
     */
    @PostMapping("case0015")
    public Map<String, Object> aTaintCase0015(@RequestBody byte[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.length < 1) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(new String(cmd));
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 数组 对象 作为污点对象
     *
     * @param cmd [{"a":"1"}]
     * @return
     */
    @PostMapping("case0016")
    public Map<String, Object> aTaintCase0016(@RequestBody SourceTestObject[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.length < 1) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd[0].getCmd());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }
    @PostMapping("case00926")
    public Map<String, Object> aTaintCase00926(@RequestBody SourceTestObject[][] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.length < 1) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd[1][1].getCmd());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 其他对象 String 作为污点对象
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0017")
    @Deprecated
    public Map<String, Object> aTaintCase0017(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", ERROR_STR);
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 其他对象 StringBuffer 作为污点对象
     *
     * @param cmd
     * @return
     */
    //@PostMapping("case0018")
    //public Map<String, Object> aTaintCase0018(@RequestBody String cmd) {
    //    Map<String, Object> modelMap = new HashMap<>();
    //    if (cmd == null) {
    //        modelMap.put("status", ERROR_STR);
    //        return modelMap;
    //    }
    //    StringBuffer data = new StringBuffer();
    //    data.append(cmd);
    //    try {
    //        Runtime.getRuntime().exec(String.valueOf(data));
    //        modelMap.put("status", SUCCESS_STR);
    //    } catch (IOException e) {
    //        modelMap.put("status", ERROR_STR);
    //    }
    //    return modelMap;
    //}
    //
    ///**
    // * 其他对象 StringBuilder 作为污点对象
    // *
    // * @param cmd
    // * @return
    // */
    //@PostMapping("case0019")
    //public Map<String, Object> aTaintCase0019(@RequestBody String cmd) {
    //    Map<String, Object> modelMap = new HashMap<>();
    //    if (cmd == null) {
    //        modelMap.put("status", ERROR_STR);
    //        return modelMap;
    //    }
    //    StringBuilder data = new StringBuilder();
    //    data.append(cmd);
    //    try {
    //        Runtime.getRuntime().exec(data.toString());
    //        modelMap.put("status", SUCCESS_STR);
    //    } catch (IOException e) {
    //        modelMap.put("status", ERROR_STR);
    //    }
    //    return modelMap;
    //}

    /**
     * 其他对象  自定义对象 对象本身作为污点对象
     *
     * @param cmd
     * @return
     */
    //@PostMapping("case0020")
    //public Map<String, Object> aTaintCase0020(@RequestBody SourceTestObject cmd) {
    //    Map<String, Object> modelMap = new HashMap<>();
    //    if (cmd == null) {
    //        modelMap.put("status", ERROR_STR);
    //        return modelMap;
    //    }
    //    try {
    //        java.io.PrintWriter printWriter = new PrintWriter(System.out);
    //        printWriter.print(cmd);
    //        //Runtime.getRuntime().exec(cmd);
    //        modelMap.put("status", SUCCESS_STR);
    //    } catch (IOException e)  {
    //        modelMap.put("status", ERROR_STR);
    //    }
    //    return modelMap;
    //}

    @PostMapping("case0021")
    @Deprecated
    public Map<String, Object> aTaintCase0021(@RequestBody SourceTestWithMPObject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd1());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping("case0021/2")
    @Deprecated
    public Map<String, Object> aTaintCase0021_2(@RequestBody SourceTestWithMPObject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd10());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping("case0021/3")
    @Deprecated
    public Map<String, Object> aTaintCase0021_3(@RequestBody SourceTestWithMPObject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd20());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 对象字段->单层字段(10)@aTaintCase00921
     *
     * @param cmd
     * @return
     */
    @PostMapping("case00921")
    public Map<String, Object> aTaintCase00921(@RequestBody SourceTestWith10Filedsbject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd1());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping("case00921/2")
    public Map<String, Object> aTaintCase00921_2(@RequestBody SourceTestWith10Filedsbject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd5());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping("case00921/3")
    public Map<String, Object> aTaintCase00921_3(@RequestBody SourceTestWith10Filedsbject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd10());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 对象字段->单层字段(10)@aTaintCase00921
     *
     * @param cmd
     * @return
     */
    @PostMapping("case00922")
    public Map<String, Object> aTaintCase00922(@RequestBody SourceTestWith100Filedsbject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd1());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping("case00922/2")
    public Map<String, Object> aTaintCase00922_2(@RequestBody SourceTestWith100Filedsbject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd50());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping("case00922/3")
    public Map<String, Object> aTaintCase00922_3(@RequestBody SourceTestWith100Filedsbject cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmd100());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }
    @PostMapping("case00923")
    public Map<String, Object> aTaintCase00923(@RequestBody LayerBaseBean2 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda0());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }
    @PostMapping("case00923/2")
    public Map<String, Object> aTaintCase00923_2(@RequestBody LayerBaseBean2 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda1());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }
    @PostMapping("case00923/3")
    public Map<String, Object> aTaintCase00923_3(@RequestBody LayerBaseBean2 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda2());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping("case00924")
    public Map<String, Object> aTaintCase00924(@RequestBody LayerBaseBean9 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda0());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }
    @PostMapping("case00924/2")
    public Map<String, Object> aTaintCase00924_2(@RequestBody LayerBaseBean9 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda1());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }
    @PostMapping("case00924/3")
    public Map<String, Object> aTaintCase00924_3(@RequestBody LayerBaseBean9 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda9());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping("case00925")
    public Map<String, Object> aTaintCase00925(@RequestBody LayerBaseBean2 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda0());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }
    @PostMapping("case00925/2")
    public Map<String, Object> aTaintCase00925_2(@RequestBody LayerBaseBean2 cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.getCmda2());
            modelMap.put("status", SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase00138 USED
     */
}
