package org.demo.demospringboot.api;

import java.lang.management.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSON;
import com.sun.management.HotSpotDiagnosticMXBean;
import org.demo.demospringboot.dto.DemoDTO;
import org.demo.demospringboot.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "vm")
public class JavaVMController {

    @GetMapping("metrics")
    public Map<String, Object> metrics() {
        Map<String, Object> map = new HashMap<>();

        List<MemoryManagerMXBean> memoryManagers = ManagementFactory.getMemoryManagerMXBeans();
        map.put("memoryManagers", JSON.parseArray(JSON.toJSONString(memoryManagers)));

        List<MemoryPoolMXBean> memoryList = ManagementFactory.getMemoryPoolMXBeans();
        List<Map<String, String>> memoryMapList = new ArrayList<>();
        for (MemoryPoolMXBean memory : memoryList) {
            Map<String, String> memoryMap = new HashMap<>();
            memoryMap.put("name", memory.getName());
            memoryMap.put("type", memory.getType().toString());
            memoryMap.put("usage", memory.getUsage().toString());
            memoryMapList.add(memoryMap);
        }
        map.put("memory", memoryMapList);

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
        return map;
    }

    @GetMapping("gc")
    public String gc() {
        System.gc();
        return "ok";
    }

    @GetMapping("object")
    public String object(@RequestParam(value = "count", required = false) String count, @RequestParam(value = "size", required = false) String size) {
        Set<DemoDTO> set = new HashSet<>();
        for (int i = 0; i < NumberUtil.parseInt(count, 0); i++) {
            set.add(new DemoDTO(i, NumberUtil.parseInt(count, 1024)));
        }
        System.out.println(set.size());
        return "ok";
    }

    @GetMapping("class")
    public String clazz(@RequestParam(value = "count", required = false) String count) {
        Set<Class<?>> set = new HashSet<>();
        for (int i = 0; i < NumberUtil.parseInt(count, 0); i++) {
            Object proxy = Proxy.newProxyInstance(DemoService.class.getClassLoader(), new Class[]{DemoService.class}, new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return "hello";
                }
            });
            set.add(proxy.getClass());
        }
        System.out.println(set.size());
        return "ok";
    }
}
