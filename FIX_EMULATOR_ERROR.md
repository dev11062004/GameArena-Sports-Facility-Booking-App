# 🔧 Fix Android Emulator Hypervisor Error - FAST SOLUTION

## ❌ Error You're Seeing:
```
[sc] ControlService FAILED 1062: The service has not been started.
[sc] StartService FAILED with error 4294967201.
```

## ✅ QUICK FIX (Choose One Method)

---

## METHOD 1: Enable Hyper-V (FASTEST - 2 minutes)

### Run as Administrator:

```powershell
# Open PowerShell as ADMINISTRATOR
# Copy-paste these commands one by one:

# Enable Hyper-V
Enable-WindowsOptionalFeature -Online -FeatureName Microsoft-Hyper-V -All

# Enable Windows Hypervisor Platform
Enable-WindowsOptionalFeature -Online -FeatureName HypervisorPlatform -All

# Restart computer
Restart-Computer
```

**After restart, emulator will work!**

---

## METHOD 2: Use BIOS Settings (If Method 1 fails)

### Step 1: Enable Virtualization in BIOS
1. **Restart computer**
2. **Press F2/F10/Del** during boot (depends on manufacturer)
3. Find **Intel VT-x** or **AMD-V** setting
4. **Enable it**
5. **Save and Exit**

### Step 2: Enable Hyper-V in Windows
```powershell
# Run PowerShell as Administrator:
bcdedit /set hypervisorlaunchtype auto
```

### Step 3: Restart computer

---

## METHOD 3: Use Android Studio's Solution (EASIEST)

### In Android Studio:
1. **Tools → SDK Manager**
2. **SDK Tools** tab
3. **Uncheck** "Intel x86 Emulator Accelerator (HAXM)"
4. **Check** "Android Emulator Hypervisor Driver for AMD Processors" 
   - OR "Intel Hardware Accelerated Execution Manager" (for Intel)
5. **Click Apply**
6. **Restart Android Studio**

---

## METHOD 4: Create ARM64 Emulator Instead (NO HYPERVISOR NEEDED)

### This bypasses the hypervisor issue completely:

1. **Device Manager** → **Create Device**
2. Select **Phone** (any model)
3. **Next** → Select System Image with **"arm64-v8a"** (not x86)
   - Look for: **"S" API 31** or **"Tiramisu" API 33** with **arm64-v8a**
   - Download if needed
4. **Next** → **Finish**

**ARM emulators don't need hypervisor!** (Slower but works immediately)

---

## ⚡ RECOMMENDED: Method 1 + Restart

### Complete Commands (Run as Admin):
```powershell
# Open PowerShell as ADMINISTRATOR (Right-click → Run as Administrator)

# Run these:
Enable-WindowsOptionalFeature -Online -FeatureName Microsoft-Hyper-V -All -NoRestart
Enable-WindowsOptionalFeature -Online -FeatureName HypervisorPlatform -All -NoRestart
bcdedit /set hypervisorlaunchtype auto

# Then restart:
Restart-Computer
```

---

## 🔍 Check if Virtualization is Enabled

### In Task Manager:
1. **Ctrl+Shift+Esc** → Open Task Manager
2. **Performance** tab
3. **CPU** section
4. Look for **"Virtualization: Enabled"**

If it says **"Disabled"** → You need to enable it in BIOS (Method 2)

---

## 🚀 FASTEST WORKAROUND (Use Physical Device)

### Skip emulator entirely:
1. **Enable USB Debugging** on your Android phone:
   - Settings → About Phone → Tap Build Number 7 times
   - Settings → Developer Options → USB Debugging ON
2. **Connect phone via USB**
3. **Select phone** in Android Studio device dropdown
4. **Click Run** ▶️

**Works immediately, no hypervisor needed!**

---

## 💻 One-Command Fix (Copy-Paste)

### For Most Windows Users:
```powershell
# COPY AND RUN THIS AS ADMINISTRATOR:
Enable-WindowsOptionalFeature -Online -FeatureName Microsoft-Hyper-V,HypervisorPlatform -All; bcdedit /set hypervisorlaunchtype auto; Write-Host "Done! Restart your computer now." -ForegroundColor Green
```

Then **restart computer**.

---

## ✅ Verify Fix Worked

After restart:
```powershell
# Check if Hyper-V is running:
Get-WindowsOptionalFeature -Online -FeatureName Microsoft-Hyper-V
# Should show: State : Enabled
```

---

## 🎯 SUMMARY - DO THIS NOW:

### FASTEST SOLUTION:
1. **Open PowerShell as Administrator**
2. **Run**: `Enable-WindowsOptionalFeature -Online -FeatureName Microsoft-Hyper-V,HypervisorPlatform -All`
3. **Type Y** when prompted
4. **Restart computer**
5. **Open Android Studio**
6. **Create/Run emulator** - WORKS! ✅

---

## Alternative: Use ARM Emulator (NO FIX NEEDED)

If you don't want to restart or enable Hyper-V:

1. Device Manager → Create Device
2. Select **arm64-v8a** system image (not x86_64)
3. Works without hypervisor!

---

## 🆘 Still Failing?

### Your computer might not support virtualization:
- Very old CPU (pre-2010)
- Virtualization locked by manufacturer
- Running in a VM already

### Solution: Use physical device or cloud emulator
- **Physical phone** (fastest)
- **Firebase Test Lab** (cloud-based)
- **BrowserStack** (cloud-based)

---

## ⏱️ Time to Fix:
- **Method 1**: 5 min (with restart)
- **Method 4 (ARM)**: 2 min (no restart)
- **Physical device**: 1 min (no fix needed)

**Choose Method 1 for best performance!**
**Choose ARM emulator for immediate testing!**

---

**MOST COMMON SOLUTION THAT WORKS:**
```powershell
# AS ADMINISTRATOR:
Enable-WindowsOptionalFeature -Online -FeatureName Microsoft-Hyper-V -All
# Restart computer
```

**DONE! Emulator will work after restart.** ✅
