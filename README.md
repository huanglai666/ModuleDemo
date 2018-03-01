# ModuleDemo
  本工程师学习多款开源项目的实现方式，实现的一种基于MVP开发模式的基础方案，通过compile本项目，可以直接使用其中抽象好的基类
  方案中选取了retrofit作为网络请求框架，利用rxjava实现响应式编程，m层并未显式给出，并使用dagger2依赖注入框架
  本方案更适合于：
    1.较大的项目，因为采用mvp框架所以会生成较多的类，但对于项目整体框架大的项目，有利于抽离逻辑和解耦
    2.需要注入逻辑较多的项目，dagger2整体比较厚重，如果注入逻辑不多可以不采用