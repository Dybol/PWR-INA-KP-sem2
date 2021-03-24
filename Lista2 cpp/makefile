SOURCE_DIR = src
BINARY_DIR = bin

CXX = g++
LD = g++

COMMON_FLAGS += -Wall
COMMON_FLAGS += -Wextra
COMMON_FLAGS += -Wpedantic
COMMON_FLAGS += -std=c++17
COMMON_FLAGS += -O3
COMMON_FLAGS += -g
COMMON_FLAGS += -iprefix$(SOURCE_DIR)/
COMMON_FLAGS += -I$(SOURCE_DIR)

CXX_FLAGS = $(COMMON_FLAGS)
CXX_FLAGS += -MMD

LD_FLAGS = $(COMMON_FLAGS)
LD_FLAGS += -lpthread

SOURCES = $(shell find $(SOURCE_DIR)/ -type f -name *.cpp)

OBJECTS := $(patsubst %.cpp,%.o,$(SOURCES))
OBJECTS := $(patsubst $(SOURCE_DIR)/%,$(BINARY_DIR)/%,$(OBJECTS))


all: main.exe

main.exe : $(OBJECTS)
	@echo LD: main.exe
	@$(LD) $(OBJECTS) -o main.exe $(LD_FLAGS)

$(BINARY_DIR)/%.o: $(SOURCE_DIR)/%.cpp | $(dir $@)
	@echo CXX: $<
	@mkdir -p $(dir $@)
	@$(CXX) $(CXX_FLAGS) -c $< -o $@

clear:
	rm -rf $(BINARY_DIR)/


-include $(OBJECTS:%.o=%.d)
