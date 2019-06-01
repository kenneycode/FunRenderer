### FunRenderer使用方法

首先在项目根gradle中配置`jitpack`：

```
allprojects {
    repositories {
        ...
        maven { url 'https://www.jitpack.io' }
    }
}
```

然后在需要使用`FunRenderer`的模块gradle中配置依赖：

```
dependencies {
    implementation 'com.github.kenneycode:FunRenderer:1.0.1'
}
```

接着就可以在代码中愉快地使用了，具体使用样例可参见本项目中的demo代码