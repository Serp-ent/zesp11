import 'package:get/get.dart';
import 'package:goadventure/app/models/decision.dart';
import 'package:goadventure/app/models/gamebook.dart';
import 'package:goadventure/app/models/step.dart';
import 'package:goadventure/app/services/game_service.dart';
import 'package:logger/logger.dart';

// This screen focuses on the active game session.
// It manages the game logic, decisions, and interactions with other players.
// TODO: split this into GameRunning and GamesController
class GameController extends GetxController {
  final GameService gameService;
  final logger = Get.find<Logger>();

  // Rx to hold the selected gamebook ID, default to null (no game selected)
  var currentGamebookId = Rx<int?>(null); // Default to null

  // Reactive variable for the selected gamebook
  Rx<Gamebook?> currentGamebook = Rx<Gamebook?>(null);

  // Reactive variable for the current step of the gamebook
  Rx<Step?> currentStep = Rx<Step?>(null);

  // List of other gamebooks (optional, depending on your requirements)
  var availableGamebooks = <Gamebook>[].obs;
  var isAvailableGamebooksLoading = false.obs;

  // Loading indicators for the gamebook and other gamebooks
  var isCurrentGamebookLoading = false.obs;
  var isOtherGamebooksLoading = true.obs;

  GameController({required this.gameService});

// History to store the sequence of decisions and steps
  var gameHistory = RxList<String>([]);

  @override
  void onInit() {
    super.onInit();

    fetchAvailableGamebooks();
    // Initialization logic if needed
  }

  // Fetch current gamebook data and the first step
  Future<void> fetchGamebookData(int id) async {
    isCurrentGamebookLoading.value = true;
    gameHistory.clear();
    try {
      final gamebook = await gameService.fetchGamebook(id);
      currentGamebook.value = gamebook;

      // Set the first step if available
      if (gamebook.steps.isNotEmpty) {
        currentStep.value = gamebook.steps.first; // Start with the first step
      }

      // Optionally, fetch other gamebooks concurrently (if needed)
      // await fetchOtherGamebooks(id);
    } catch (e) {
      logger.e("Error fetching gamebook: $e");
    } finally {
      isCurrentGamebookLoading.value = false;
    }
  }

  // Update the current gamebook and trigger a re-fetch
  void updateCurrentGamebook(int id) {
    currentGamebookId.value = id;
    fetchGamebookData(id);
  }

  void makeDecision(Decision decision) {
    // Add the current step text to the history
    // TODO: I think this should fetch from the remote server
    // TODO: adjust the developmentApiService then
    if (currentStep.value != null) {
      gameHistory.add("Step: ${currentStep.value!.text}");
    }

    // Add the decision text to the history
    gameHistory.add("Decision: ${decision.text}");

    // Find the next step based on the decision
    Step? nextStep = currentGamebook.value?.steps.firstWhere(
      (step) => step.id == decision.nextStepId,
      orElse: () => Step(
        id: decision.nextStepId,
        title: "Next Step",
        text: "This is the next step.",
        latitude: 0.0,
        longitude: 0.0,
        decisions: [],
      ),
    );

    if (nextStep != null) {
      // Update the current step to the next one
      currentStep.value = nextStep;
    }
  }

  // Check if a game is selected
  bool isGamebookSelected() {
    return currentGamebook.value != null;
  }

  // Get the game history as a string
  String getGameHistory() {
    if (gameHistory.isEmpty) {
      return "There is no history yet, travel around the world to create your own...";
    } else {
      return gameHistory.join('\n'); // Join the history items with newline
    }
  }

  // TODO: add error handling
  Future<void> fetchAvailableGamebooks() async {
    isAvailableGamebooksLoading.value = true;
    try {
      logger.i("[DEV_DEBUG] Fetching available gamebooks");
      final gamebooks = await gameService.fetchAvailableGamebooks();

      availableGamebooks.assignAll(gamebooks);
    } catch (e) {
      logger.e("Error fetching available gamebooks: $e");
    } finally {
      isAvailableGamebooksLoading.value = false;
    }
  }
}
