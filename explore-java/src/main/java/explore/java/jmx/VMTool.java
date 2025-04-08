package explore.java.jmx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.management.HotSpotDiagnosticMXBean;

import java.lang.management.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VMTool {

    public static Map<String, Object> getVMInfo() {
        Map<String, Object> map = new HashMap<>();

        List<MemoryManagerMXBean> memoryManagers = ManagementFactory.getMemoryManagerMXBeans();
        map.put("memoryManagers", JSON.parseArray(JSON.toJSONString(memoryManagers)));

        map.put("memory", getVMMemoryInfo());

        ThreadMXBean thread = ManagementFactory.getThreadMXBean();
        map.put("thread", JSON.parseObject(JSON.toJSONString(thread)));

        List<GarbageCollectorMXBean> garbage = ManagementFactory.getGarbageCollectorMXBeans();
        map.put("garbage", JSON.parseArray(JSON.toJSONString(garbage)));

        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        map.put("runtime", JSON.parseObject(JSON.toJSONString(runtime)));

        ClassLoadingMXBean classloading = ManagementFactory.getClassLoadingMXBean();
        map.put("classloading", JSON.parseObject(JSON.toJSONString(classloading)));

        OperatingSystemMXBean operatingSystem = ManagementFactory.getOperatingSystemMXBean();
        map.put("operatingSystem", JSON.parseObject(JSON.toJSONString(operatingSystem)));

        CompilationMXBean compilation = ManagementFactory.getCompilationMXBean();
        map.put("compilation", JSON.parseObject(JSON.toJSONString(compilation)));

        HotSpotDiagnosticMXBean hotSpotDiagnostic = ManagementFactory.getPlatformMXBean(HotSpotDiagnosticMXBean.class);
        map.put("hotSpotDiagnostic", JSON.toJSONString(hotSpotDiagnostic.getDiagnosticOptions()));
        return map;
    }

    public static JSONArray getVMMemoryInfo() {
        JSONArray memoryInfo = new JSONArray();
        List<MemoryPoolMXBean> memoryList = ManagementFactory.getMemoryPoolMXBeans();
        for (MemoryPoolMXBean memory : memoryList) {
            JSONObject memoryJson = new JSONObject();
            memoryJson.put("name", memory.getName());
            memoryJson.put("type", memory.getType().toString());
            memoryJson.put("usage", memory.getUsage().toString());
            memoryInfo.add(memoryJson);
        }
        return memoryInfo;
    }
}
