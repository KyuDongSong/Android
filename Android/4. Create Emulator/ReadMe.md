## Create Emulator
> 실제 Device를 사용하는 분이라면, Android Virtual Device 를 설정할 필요가 없습니다.

### Step 1. AVD(Android Virtual Device) Manager 실행하기
<img src="./imgs/1. AVD Manager.png" />
Android Project 화면에서 AVD Manager 실행합니다.

### Step 2. Your Virtual Devices 실행하기
<img src="./imgs/2. Your Virtual Devices.png" />
등록된 AVD(Android Virtual Device)를 보여주는 화면입니다.
지금은 등록되어 있는 AVD가 없으니 아무것도 나타나지 않습니다.
화면 중앙의 `Create Virtual Device...` 를 클릭합니다.

### Step 3. Select Hardware
<img src="./imgs/3. Select Hardware.png" />
Android Virtual Device 의 하드웨어 모델을 선택합니다.
해상도가 높을수록 선명하겠지만 반대로 높은 성능을 요구합니다.
성능에 맞도록 해상도를 선택합니다.

만약 성능이 이미 높다면, 기본 선택된 값(`Nexus 5`)으로 사용합니다.

Next를 클릭해 다음으로 이동합니다.

### Step 4. System Image
<img src="./imgs/4. System Image.png" />
선택한 하드웨어에 사용할 Android OS(Operating System)를 선택합니다.
자신의 컴퓨터 CPU가 32bit라면 ABI 가 `x86`인것으로, 64bit라면 `x86_64`를 선택하세요.

필요한 System Image가 준비되지 않았다면 Download 링크가 나타납니다. Download를 클릭해서 사용 준비를 해주세요.

Next를 클릭해 다음으로 이동합니다.

### Step 5. Android Virtual Device (AVD)
<img src="./imgs/5. Android Virtual Device (AVD).png" />
하드웨어를 설정합니다. 메모리의 용량이나 카메라의 유무 등을 선택하게 됩니다.

`Show Advanced Settings`를 클릭하면, 카메라, CPU 등을 선택할 수 있습니다.

Finish를 클릭해 AVD 설정을 완료합니다.

### Step 6. Android Virtual Devices
<img src="./imgs/6. Android Virtual Devices.png" />
AVD를 만들고 나면 위 그림처럼 AVD 목록이 나타납니다. 지금 화면에서 우측의 녹색 세모 버튼을 누르면 에뮬레이터가 실행됩니다.

<img src="./imgs/Android.png" />
에뮬레이터가 실행되었습니다.
