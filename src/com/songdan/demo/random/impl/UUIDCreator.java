package com.songdan.demo.random.impl;

import java.util.UUID;

import com.songdan.demo.random.IMajorKey;



/**
 * 生成UUID型主键
 * 
 * @author jwh
 */

public class UUIDCreator implements IMajorKey {
	
    @Override
    public String getMajorKey() {
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        return uuid;
    }
}
