package com.PBJ.ChatRoom_Backend.util;

import java.util.Map;

public record ApiResponse<T>(String name, Map<String, Object> payload) { }
