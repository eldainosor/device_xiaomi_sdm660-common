/*
 *
 *  Copyright (c) 2013, The Linux Foundation. All rights reserved.
 *  Not a Contribution, Apache license notifications and license are retained
 *  for attribution purposes only.
 *
 * Copyright (C) 2012 The Android Open Source Project
 * Copyright (C) 2016 The CyanogenMod Project
 * Copyright (C) 2020 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#ifndef _BDROID_BUILDCFG_H
#define _BDROID_BUILDCFG_H

#include <stdint.h>
#pragma push_macro("PROPERTY_VALUE_MAX")

#include <cutils/properties.h>
#include <string.h>

static inline const char* BtmGetDefaultName()
{
    char product_model[PROPERTY_VALUE_MAX];
    property_get("ro.product.model", product_model, "");

    if (strstr(product_model, "Mi A2"))
        return "Mi A2";
    if (strstr(product_model, "MI 6X"))
        return "MI 6X";
    if (strstr(product_model, "MI PAD 4"))
        return "MI PAD 4";
    if (strstr(product_model, "MI PAD 4 PLUS"))
        return "MI PAD 4 PLUS";

    // Fallback to ro.product.model
    return "";
}
#undef PROPERTY_VALUE_MAX

#define BTM_DEF_LOCAL_NAME BtmGetDefaultName()
// Disables read remote device feature
#define MAX_ACL_CONNECTIONS   16
#define MAX_L2CAP_CHANNELS    16
#define BLE_VND_INCLUDED   TRUE

/* Increasing SEPs to 12 from 6 to support SHO/MCast i.e. two streams per codec */
#define AVDT_NUM_SEPS 12
#pragma pop_macro("PROPERTY_VALUE_MAX")

#endif
