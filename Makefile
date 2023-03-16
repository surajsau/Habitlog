PRODUCT_NAME := Habit Log

PACKAGE := jp.suji.habit

.PHONY: deeplink
deeplink: ## deeplink開く。例、deeplink link=sneak://talks
	adb shell am start -W -a android.intent.action.VIEW -d "$(link)" $(PACKAGE)

.PHONY: lintcheck
lintcheck: ## Detekt と Lint チェック
	bundle exec fastlane lint

.PHONY: build
build: ## build する
	./gradlew build

.PHONY: clean-build
clean-build: ## build する
	./gradlew clean build

.PHONY: install
install: ## debugアプリをビルドしてインストールする
	./gradlew assembleDebug
	adb install -r app/build/outputs/apk/debug/app-debug.apk
	adb shell am start -n $(PACKAGE)/.MainActivity

.PHONY: uninstall
uninstall: ## debugアプリをアンインストールする
	adb shell pm clear $(PACKAGE)
	adb uninstall $(PACKAGE)

.PHONY: snap
snap: ## スクリーンショット撮って開く
	$(eval TIMESTAMP := $(shell date +%Y%m%d%H%M%S))
	mkdir -p ~/Desktop/screenshots
	adb shell screencap -p /sdcard/screen-${TIMESTAMP}.png
	adb pull /sdcard/screen-${TIMESTAMP}.png ~/Desktop/screenshots
	open ~/Desktop/screenshots/screen-${TIMESTAMP}.png

.PHONY: install-debug
install-debug: ## debug アプリをビルドしてインストールする
	./gradlew assembleDebug
	adb install -r app/build/outputs/apk/debug/app-debug.apk

.PHONY: generate-golden
generate-golden: ## Golden screenhots の生成
	./gradlew recordPaparazziDebug

.PHONY: test-screenshot
test-screenshot: ## Screenshot テストを実行する
	./gradlew clean verifyPaparazziDebug

.PHONY: help
.DEFAULT_GOAL := help
help: ## Display this help screen.
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'